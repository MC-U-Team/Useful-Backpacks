package info.u_team.useful_backpacks.container;

import info.u_team.useful_backpacks.init.UsefulBackpacksContainerTypes;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class EnderChestBackpackContainer extends ChestContainer {
	
	private final int selectedSlot;
	
	// Client
	public static EnderChestBackpackContainer createEnderChestContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		final int selectedSlot = buffer.readVarInt();
		return createEnderChestContainer(id, playerInventory, new Inventory(9 * 3), selectedSlot);
	}
	
	// Server
	public static EnderChestBackpackContainer createEnderChestContainer(int id, PlayerInventory playerInventory, IInventory inventory, int selectedSlot) {
		return new EnderChestBackpackContainer(UsefulBackpacksContainerTypes.ENDERCHEST_BACKPACK.get(), id, playerInventory, inventory, 3, selectedSlot);
	}
	
	public EnderChestBackpackContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IInventory inventory, int rows, int selectedSlot) {
		super(type, id, playerInventory, inventory, rows);
		this.selectedSlot = selectedSlot;
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
	
}
