package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.api.sync.MessageHolder;
import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.container.slot.ItemFilterSlot;
import info.u_team.useful_backpacks.init.UsefulBackpacksContainerTypes;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

public class ItemFilterContainer extends UContainer {
	
	private final ItemStack filterStack;
	private final int selectedSlot;
	private final boolean isStrictInitial;
	
	private final IInventory filterItemSlotInventory = new Inventory(1);
	
	private final MessageHolder strictMessage;
	
	public ItemFilterContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(id, playerInventory, ItemStack.EMPTY, buffer.readVarInt(), buffer.readBoolean());
	}
	
	public ItemFilterContainer(int id, PlayerInventory playerInventory, ItemStack filterStack, int selectedSlot, boolean isStrictInitial) {
		super(UsefulBackpacksContainerTypes.ITEM_FILTER.get(), id);
		this.filterStack = filterStack;
		this.selectedSlot = selectedSlot;
		this.isStrictInitial = isStrictInitial;
		
		final CompoundNBT compound = filterStack.getChildTag("stack");
		if (compound != null) {
			filterItemSlotInventory.setInventorySlotContents(0, ItemStack.read(compound));
		}
		
		appendInventory(filterItemSlotInventory, ItemFilterSlot::new, 1, 1, 17, 17);
		appendPlayerInventory(playerInventory, 8, 48);
		
		strictMessage = addClientToServerTracker(new MessageHolder(buffer -> {
			final boolean strict = buffer.readBoolean();
			if (!filterStack.isEmpty()) {
				if (!strict) {
					filterStack.removeChildTag("strict");
				} else {
					filterStack.getOrCreateTag().putBoolean("strict", strict);
				}
			}
		}));
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if (!filterStack.isEmpty()) {
			final ItemStack stackToFilter = filterItemSlotInventory.getStackInSlot(0);
			if (stackToFilter.isEmpty()) {
				filterStack.removeChildTag("stack");
			} else {
				stackToFilter.write(filterStack.getOrCreateChildTag("stack"));
			}
		}
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickType, PlayerEntity player) {
		if (slotId == 0) {
			return filterSlotClick(dragType, clickType, player);
		}
		
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
	
	private ItemStack filterSlotClick(int dragType, ClickType clickType, PlayerEntity player) {
		final ItemStack stack;
		
		if (clickType == ClickType.THROW) {
			filterItemSlotInventory.setInventorySlotContents(0, ItemStack.EMPTY);
			stack = ItemStack.EMPTY;
		} else if (clickType == ClickType.PICKUP || clickType == ClickType.CLONE) {
			stack = player.inventory.getItemStack().copy();
			stack.setCount(1);
			filterItemSlotInventory.setInventorySlotContents(0, stack);
		} else if (clickType == ClickType.SWAP) {
			stack = player.inventory.getStackInSlot(dragType).copy();
			stack.setCount(1);
			filterItemSlotInventory.setInventorySlotContents(0, stack);
		} else {
			stack = ItemStack.EMPTY;
		}
		return stack;
	}
	
	public boolean isStrictInitial() {
		return isStrictInitial;
	}
	
	public MessageHolder getStrictMessage() {
		return strictMessage;
	}
}
