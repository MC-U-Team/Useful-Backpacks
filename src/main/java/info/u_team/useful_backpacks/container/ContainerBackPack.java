package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerBackPack extends UContainer {
	
	private InventoryBackPack backpackInventory;
	private EnumBackPacks type;
	
	public ContainerBackPack(InventoryPlayer playerInventory, InventoryBackPack backpackInventory) {
		
		this.backpackInventory = backpackInventory;
		type = backpackInventory.getType();
		
		int xBackpack = 0;
		int yBackpack = 0;
		
		int xPlayer = 0;
		int yPlayer = 0;
		
		switch (type) {
		case SMALL:
			xBackpack = 44;
			yBackpack = 24;
			
			xPlayer = 8;
			yPlayer = 82;
			break;
		case MEDIUM:
			xBackpack = 8;
			yBackpack = 24;
			
			xPlayer = 8;
			yPlayer = 136;
			break;
		case LARGE:
			xBackpack = 8;
			yBackpack = 24;
			
			xPlayer = 44;
			yPlayer = 190;
			break;
		}
		
		appendBackPackInventory(backpackInventory, xBackpack, yBackpack);
		appendPlayerInventory(playerInventory, xPlayer, yPlayer);
	}
	
	public void appendBackPackInventory(InventoryBackPack inventory, int x_offset, int y_offset) {
		for (int height = 0; height < type.getSizeY(); height++) {
			for (int width = 0; width < type.getSizeX(); width++) {
				addSlot(new SlotBackPack(inventory, width + height * type.getSizeX(), width * 18 + x_offset, height * 18 + y_offset));
			}
		}
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		backpackInventory.writeItemStack();
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
