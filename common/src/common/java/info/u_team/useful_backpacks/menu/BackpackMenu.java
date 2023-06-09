package info.u_team.useful_backpacks.menu;

import info.u_team.u_team_core.menu.UContainerMenu;
import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.init.UsefulBackpacksMenuTypes;
import info.u_team.useful_backpacks.inventory.BackpackInventory;
import info.u_team.useful_backpacks.menu.slot.BackpackSlot;
import info.u_team.useful_backpacks.type.BackpackType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BackpackMenu extends UContainerMenu {
	
	private final Container backpackInventory;
	private final BackpackType backpack;
	private final int selectedSlot;
	
	// Client
	public static BackpackMenu createClientContainer(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
		final BackpackType backpack = buffer.readEnum(BackpackType.class);
		final int selectedSlot = buffer.readVarInt();
		return new BackpackMenu(id, playerInventory, new SimpleContainer(backpack.getInventorySize()), backpack, selectedSlot);
	}
	
	// Server
	public BackpackMenu(int id, Inventory playerInventory, Container backpackInventory, BackpackType backpack, int selectedSlot) {
		super(UsefulBackpacksMenuTypes.BACKPACK.get(), id);
		this.backpackInventory = backpackInventory;
		this.backpack = backpack;
		this.selectedSlot = selectedSlot;
		addBackpackInventory(backpack.getSlotBackpackX(), backpack.getSlotBackpackY());
		addPlayerInventory(playerInventory, backpack.getSlotPlayerX(), backpack.getSlotPlayerY());
	}
	
	public void addBackpackInventory(int x, int y) {
		for (int height = 0; height < backpack.getInventoryHeight(); height++) {
			for (int width = 0; width < backpack.getInventoryWidth(); width++) {
				addSlot(new BackpackSlot(backpackInventory, width + height * backpack.getInventoryWidth(), width * 18 + x, height * 18 + y));
			}
		}
	}
	
	@Override
	public void broadcastChanges() {
		super.broadcastChanges();
		if (backpackInventory instanceof final BackpackInventory inventory) {
			inventory.writeItemStack();
		}
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = slots.get(index);
		
		if (slot != null && slot.hasItem()) {
			final ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			
			if (index < backpack.getInventorySize()) {
				if (!moveItemStackTo(itemstack1, backpack.getInventorySize(), slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!moveItemStackTo(itemstack1, 0, backpack.getInventorySize(), false)) {
				return ItemStack.EMPTY;
			}
			
			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return itemstack;
	}
	
	@Override
	public void clicked(int slotId, int dragType, ClickType clickType, Player player) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < slots.size()) {
			tmpSlot = slots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.container == player.getInventory() && tmpSlot.getSlotIndex() == selectedSlot) {
				return;
			}
		}
		if (clickType == ClickType.SWAP) {
			final ItemStack stack = player.getInventory().getItem(dragType);
			final ItemStack currentItem = Inventory.isHotbarSlot(selectedSlot) ? player.getInventory().items.get(selectedSlot) : selectedSlot == -1 ? player.getInventory().offhand.get(0) : ItemStack.EMPTY;
			
			if (!currentItem.isEmpty() && stack == currentItem) {
				return;
			}
		}
		super.clicked(slotId, dragType, clickType, player);
	}
	
	@Override
	public boolean stillValid(Player player) {
		if (backpackInventory instanceof final BackpackInventory inventory) {
			final ItemStack stack = inventory.getStack();
			return !stack.isEmpty() && stack.getItem() instanceof Backpack;
		}
		return true;
	}
	
	public BackpackType getBackpack() {
		return backpack;
	}
	
	public Container getBackpackInventory() {
		return backpackInventory;
	}
}
