package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksContainerTypes;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class TagFilterContainer extends UContainer {
	
	private final ItemStack filterStack;
	private final int selectedSlot;
	
	public TagFilterContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(id, playerInventory, ItemStack.EMPTY, buffer.readVarInt());
	}
	
	public TagFilterContainer(int id, PlayerInventory playerInventory, ItemStack filterStack, int selectedSlot) {
		super(UsefulBackpacksContainerTypes.TAG_FILTER.get(), id);
		this.filterStack = filterStack;
		this.selectedSlot = selectedSlot;
		
		appendPlayerInventory(playerInventory, 8, 48);
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
