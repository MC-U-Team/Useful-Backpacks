package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksContainerType;
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
	
	public static BackpackContainer createClientContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		final Backpack backPack = buffer.readEnumValue(Backpack.class);
		return new BackpackContainer(id, playerInventory, backPack);
	}
	
	private BackpackContainer(int id, PlayerInventory playerInventory, Backpack backpack) {
		this(id, playerInventory, new Inventory(backpack.getInventorySize()), backpack);
	}
	
	public BackpackContainer(int id, PlayerInventory playerInventory, IInventory backpackInventory, Backpack backpack) {
		super(UsefulBackpacksContainerType.TYPE, id);
		this.backpackInventory = backpackInventory;
		this.backpack = backpack;
		appendBackpackInventory(backpack.getSlotBackpackX(), backpack.getSlotBackpackY());
		appendPlayerInventory(playerInventory, backpack.getSlotPlayerX(), backpack.getSlotPlayerY());
	}
	
	public void appendBackpackInventory(int x, int y) {
		for (int height = 0; height < backpack.getInventoryHeight(); height++) {
			for (int width = 0; width < backpack.getInventoryWidth(); width++) {
				addSlot(new Slot(backpackInventory, width + height * backpack.getInventoryWidth(), width * 18 + x, height * 18 + y));
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
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
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
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < inventorySlots.size()) {
			tmpSlot = inventorySlots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.inventory == player.inventory && tmpSlot.getSlotIndex() == player.inventory.currentItem) {
				return tmpSlot.getStack();
			}
		}
		if (clickTypeIn == ClickType.SWAP) {
			ItemStack stack = player.inventory.getStackInSlot(dragType);
			if (stack == player.inventory.getCurrentItem()) {
				return ItemStack.EMPTY;
			}
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
	
	public Backpack getBackpack() {
		return backpack;
	}
}
