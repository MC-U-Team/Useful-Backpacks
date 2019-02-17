package info.u_team.useful_backpacks.container;

import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerBackPack extends Container {
	
	private InventoryBackPack backpackInventory;
	private EnumBackPacks type;
	
	public ContainerBackPack(InventoryPlayer playerInventory, InventoryBackPack backpackInventory) {
		
		this.backpackInventory = backpackInventory;
		this.type = backpackInventory.getType();
		
		int x_backpackinv = 0;
		int y_backpackinv = 0;
		
		int x_playerinv = 0;
		int y_playerinv = 0;
		
		switch (type) {
		case SMALL:
			x_backpackinv = 44;
			y_backpackinv = 24;
			
			x_playerinv = 8;
			y_playerinv = 82;
			break;
		case MEDIUM:
			x_backpackinv = 8;
			y_backpackinv = 24;
			
			x_playerinv = 8;
			y_playerinv = 136;
			break;
		case LARGE:
			x_backpackinv = 8;
			y_backpackinv = 24;
			
			x_playerinv = 44;
			y_playerinv = 190;
			break;
		}
		
		drawBackPackInventory(backpackInventory, x_backpackinv, y_backpackinv);
		drawPlayerInventory(playerInventory, x_playerinv, y_playerinv);
	}
	
	public void drawBackPackInventory(InventoryBackPack inventory, int x_offset, int y_offset) {
		for (int height = 0; height < type.getSizeY(); height++) {
			for (int width = 0; width < type.getSizeX(); width++) {
				addSlot(new SlotBackPack(inventory, width + height * type.getSizeX(), width * 18 + x_offset, height * 18 + y_offset));
			}
		}
	}
	
	public void drawPlayerInventory(InventoryPlayer inventory, int x_offset, int y_offset) {
		for (int height = 0; height < 4; height++) {
			for (int width = 0; width < 9; width++) {
				if (height == 3) {
					addSlot(new Slot(inventory, width, width * 18 + x_offset, height * 18 + 4 + y_offset));
					continue;
				}
				addSlot(new Slot(inventory, width + height * 9 + 9, width * 18 + x_offset, height * 18 + y_offset));
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		backpackInventory.writeItemStack(backpackInventory.getStack());
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (index < type.getCount()) {
				if (!this.mergeItemStack(itemstack1, type.getCount(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, type.getCount(), false)) {
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
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < inventorySlots.size()) {
			tmpSlot = (Slot) inventorySlots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.isHere(player.inventory, player.inventory.currentItem)) {
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
	
}
