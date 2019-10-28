package info.u_team.useful_backpacks.container;

import info.u_team.useful_backpacks.init.UsefulBackpacksContainerTypes;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;

public class EnderChestBackpackContainer extends ChestContainer {
	
	// Client
	public static EnderChestBackpackContainer createEnderChestContainer(int id, PlayerInventory playerInventory) {
		return createEnderChestContainer(id, playerInventory, new Inventory(9 * 3));
	}
	
	// Server
	public static EnderChestBackpackContainer createEnderChestContainer(int id, PlayerInventory playerInventory, IInventory inventory) {
		return new EnderChestBackpackContainer(UsefulBackpacksContainerTypes.ENDERCHEST_BACKPACK, id, playerInventory, inventory, 3);
	}
	
	public EnderChestBackpackContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IInventory inventory, int rows) {
		super(type, id, playerInventory, inventory, rows);
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
			if (tmpSlot.inventory == player.inventory && tmpSlot.getSlotIndex() == player.inventory.currentItem) {
				return tmpSlot.getStack();
			}
		}
		if (clickType == ClickType.SWAP) {
			ItemStack stack = player.inventory.getStackInSlot(dragType);
			if (stack == player.inventory.getCurrentItem()) {
				return ItemStack.EMPTY;
			}
		}
		return super.slotClick(slotId, dragType, clickType, player);
	}
	
}
