package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.init.UsefulBackpacksContainerTypes;
import info.u_team.useful_backpacks.inventory.BackpackInventory;
import info.u_team.useful_backpacks.type.Backpack;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class BackpackContainer extends UContainer {
	
	private final IInventory backpackInventory;
	private final Backpack backpack;
	private final int selectedSlot;
	
	// Client
	public static BackpackContainer createClientContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		final Backpack backpack = buffer.readEnumValue(Backpack.class);
		final int selectedSlot = buffer.readVarInt();
		return new BackpackContainer(id, playerInventory, new Inventory(backpack.getInventorySize()), backpack, selectedSlot);
	}
	
	// Server
	public BackpackContainer(int id, PlayerInventory playerInventory, IInventory backpackInventory, Backpack backpack, int selectedSlot) {
		super(UsefulBackpacksContainerTypes.BACKPACK.get(), id);
		this.backpackInventory = backpackInventory;
		this.backpack = backpack;
		this.selectedSlot = selectedSlot;
		appendBackpackInventory(backpack.getSlotBackpackX(), backpack.getSlotBackpackY());
		appendPlayerInventory(playerInventory, backpack.getSlotPlayerX(), backpack.getSlotPlayerY());
	}
	
	public void appendBackpackInventory(int x, int y) {
		for (int height = 0; height < backpack.getInventoryHeight(); height++) {
			for (int width = 0; width < backpack.getInventoryWidth(); width++) {
				addSlot(new BackpackSlot(backpackInventory, width + height * backpack.getInventoryWidth(), width * 18 + x, height * 18 + y));
			}
		}
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if (backpackInventory instanceof BackpackInventory) {
			((BackpackInventory) backpackInventory).writeItemStack();
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			final ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (index < backpack.getInventorySize()) {
				if (!this.mergeItemStack(itemstack1, backpack.getInventorySize(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, backpack.getInventorySize(), false)) {
				return ItemStack.EMPTY;
			}
			
			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickType, PlayerEntity player) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < inventorySlots.size()) {
			tmpSlot = inventorySlots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.inventory == player.inventory && tmpSlot.getSlotIndex() == selectedSlot) {
				return tmpSlot.getStack();
			}
		}
		if (clickType == ClickType.SWAP) {
			final ItemStack stack = player.inventory.getStackInSlot(dragType);
			final ItemStack currentItem = PlayerInventory.isHotbar(selectedSlot) ? player.inventory.mainInventory.get(selectedSlot) : selectedSlot == -1 ? player.inventory.offHandInventory.get(0) : ItemStack.EMPTY;
			
			if (!currentItem.isEmpty() && stack == currentItem) {
				return ItemStack.EMPTY;
			}
		}
		return super.slotClick(slotId, dragType, clickType, player);
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity player) {
		if (backpackInventory instanceof BackpackInventory) {
			final ItemStack stack = ((BackpackInventory) backpackInventory).getStack();
			return !stack.isEmpty() && stack.getItem() instanceof IBackpack;
		}
		return true;
	}
	
	public Backpack getBackpack() {
		return backpack;
	}
}
