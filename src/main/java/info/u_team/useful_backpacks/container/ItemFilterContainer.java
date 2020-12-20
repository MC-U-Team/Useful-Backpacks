package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksContainerTypes;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ItemFilterContainer extends UContainer {
	
	private final ItemStack stack;
	private final int selectedSlot;
	
	private final IInventory filterItemSlotInventory = new Inventory(1) {
		
		@Override
		public void markDirty() {
			super.markDirty();
			onCraftMatrixChanged(this);
		}
	};
	
	public ItemFilterContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(id, playerInventory, ItemStack.EMPTY, buffer.readVarInt());
	}
	
	public ItemFilterContainer(int id, PlayerInventory playerInventory, ItemStack stack, int selectedSlot) {
		super(UsefulBackpacksContainerTypes.ITEM_FILTER.get(), id);
		this.stack = stack;
		this.selectedSlot = selectedSlot;
		
		appendInventory(filterItemSlotInventory, 1, 1, 10, 10);
		appendPlayerInventory(playerInventory, 8, 84);
	}
	
	@Override
	public void detectAndSendChanges() {
		if (!stack.isEmpty()) {
			final ItemStack stackToFilter = filterItemSlotInventory.getStackInSlot(0);
			if (stackToFilter.isEmpty()) {
				stack.removeChildTag("stack");
			} else {
				stackToFilter.write(stack.getOrCreateChildTag("stack"));
			}
		}
		super.detectAndSendChanges();
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
