package info.u_team.useful_backpacks.menu;

import info.u_team.u_team_core.api.sync.MessageHolder;
import info.u_team.u_team_core.menu.UContainerMenu;
import info.u_team.useful_backpacks.component.ItemFilterComponent;
import info.u_team.useful_backpacks.init.UsefulBackpacksDataComponentTypes;
import info.u_team.useful_backpacks.init.UsefulBackpacksMenuTypes;
import info.u_team.useful_backpacks.menu.slot.ItemFilterSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ItemFilterMenu extends UContainerMenu {
	
	private final ItemStack filterStack;
	private final int selectedSlot;
	private boolean isStrict;
	
	private final Container filterItemSlotInventory = new SimpleContainer(1);
	
	private final MessageHolder strictMessage;
	
	public ItemFilterMenu(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
		this(id, playerInventory, ItemStack.EMPTY, buffer.readVarInt(), buffer.readBoolean());
	}
	
	public ItemFilterMenu(int id, Inventory playerInventory, ItemStack filterStack, int selectedSlot, boolean isStrict) {
		super(UsefulBackpacksMenuTypes.ITEM_FILTER.get(), id);
		this.filterStack = filterStack;
		this.selectedSlot = selectedSlot;
		this.isStrict = isStrict;
		
		final ItemFilterComponent component = filterStack.get(UsefulBackpacksDataComponentTypes.ITEM_FILTER_COMPONENT.get());
		if (component != null && component.isPresent()) {
			filterItemSlotInventory.setItem(0, component.getStack());
		}
		
		addSlots((index, xPosition, yPosition) -> new ItemFilterSlot(filterItemSlotInventory, index, xPosition, yPosition), 1, 1, 17, 17);
		addPlayerInventory(playerInventory, 8, 48);
		
		strictMessage = addDataHolderToServer(new MessageHolder(buffer -> {
			final boolean newIsStrict = buffer.readBoolean();
			if (!filterStack.isEmpty()) {
				filterStack.update(UsefulBackpacksDataComponentTypes.ITEM_FILTER_COMPONENT.get(), ItemFilterComponent.EMPTY, old -> {
					if (!old.isPresent()) {
						return ItemFilterComponent.EMPTY;
					} else {
						return ItemFilterComponent.of(newIsStrict, old.getStack());
					}
				});
			}
		}));
	}
	
	@Override
	public boolean stillValid(Player player) {
		return true;
	}
	
	@Override
	public void broadcastChanges() {
		if (!filterStack.isEmpty()) {
			final ItemStack stackToFilter = filterItemSlotInventory.getItem(0);
			
			filterStack.update(UsefulBackpacksDataComponentTypes.ITEM_FILTER_COMPONENT.get(), ItemFilterComponent.EMPTY, old -> {
				return ItemFilterComponent.of(old.isStrict(), stackToFilter);
			});
		}
		
		super.broadcastChanges();
	}
	
	@Override
	public void clicked(int slotId, int dragType, ClickType clickType, Player player) {
		if (slotId == 0) {
			filterSlotClick(dragType, clickType, player);
			return;
		}
		
		Slot tmpSlot;
		if (slotId >= 0 && slotId < slots.size()) {
			tmpSlot = slots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.container == player.getInventory() && tmpSlot.getContainerSlot() == selectedSlot) {
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
	
	private ItemStack filterSlotClick(int dragType, ClickType clickType, Player player) {
		final ItemStack stack;
		
		if (clickType == ClickType.THROW) {
			filterItemSlotInventory.setItem(0, ItemStack.EMPTY);
			stack = ItemStack.EMPTY;
		} else if (clickType == ClickType.PICKUP || clickType == ClickType.CLONE) {
			stack = getCarried().copy();
			stack.setCount(1);
			filterItemSlotInventory.setItem(0, stack);
		} else if (clickType == ClickType.SWAP) {
			stack = player.getInventory().getItem(dragType).copy();
			stack.setCount(1);
			filterItemSlotInventory.setItem(0, stack);
		} else {
			stack = ItemStack.EMPTY;
		}
		return stack;
	}
	
	public boolean isStrict() {
		return isStrict;
	}
	
	public void setStrict(boolean isStrict) {
		this.isStrict = isStrict;
	}
	
	public MessageHolder getStrictMessage() {
		return strictMessage;
	}
	
	public boolean hasFilterItem() {
		return !filterItemSlotInventory.getItem(0).isEmpty();
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = slots.get(index);
		
		if (slot != null && slot.hasItem()) {
			final ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			
			if (index < 1) {
				return ItemStack.EMPTY;
			} else {
				final ItemStack stack = itemstack1.copy();
				stack.setCount(1);
				filterItemSlotInventory.setItem(0, stack);
				return ItemStack.EMPTY;
			}
		}
		return itemstack;
	}
	
}
