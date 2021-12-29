package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.api.sync.MessageHolder;
import info.u_team.u_team_core.menu.UContainerMenu;
import info.u_team.useful_backpacks.container.slot.ItemFilterSlot;
import info.u_team.useful_backpacks.init.UsefulBackpacksMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ItemFilterContainer extends UContainerMenu {
	
	private final ItemStack filterStack;
	private final int selectedSlot;
	private boolean isStrict;
	
	private final Container filterItemSlotInventory = new SimpleContainer(1);
	
	private final MessageHolder strictMessage;
	
	public ItemFilterContainer(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
		this(id, playerInventory, ItemStack.EMPTY, buffer.readVarInt(), buffer.readBoolean());
	}
	
	public ItemFilterContainer(int id, Inventory playerInventory, ItemStack filterStack, int selectedSlot, boolean isStrict) {
		super(UsefulBackpacksMenuTypes.ITEM_FILTER.get(), id);
		this.filterStack = filterStack;
		this.selectedSlot = selectedSlot;
		this.isStrict = isStrict;
		
		final var compound = filterStack.getTagElement("stack");
		if (compound != null) {
			filterItemSlotInventory.setItem(0, ItemStack.of(compound));
		}
		
		addSlots(filterItemSlotInventory, ItemFilterSlot::new, 1, 1, 17, 17);
		addPlayerInventory(playerInventory, 8, 48);
		
		strictMessage = addDataHolderToServer(new MessageHolder(buffer -> {
			final var newIsStrict = buffer.readBoolean();
			if (!filterStack.isEmpty()) {
				if (!newIsStrict) {
					filterStack.removeTagKey("strict");
				} else {
					filterStack.getOrCreateTag().putBoolean("strict", newIsStrict);
				}
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
			final var stackToFilter = filterItemSlotInventory.getItem(0);
			if (stackToFilter.isEmpty()) {
				filterStack.removeTagKey("stack");
			} else {
				stackToFilter.save(filterStack.getOrCreateTagElement("stack"));
			}
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
			if (tmpSlot.container == player.getInventory() && tmpSlot.getSlotIndex() == selectedSlot) {
				// return tmpSlot.getItem(); // TODO right?
				return;
			}
		}
		if (clickType == ClickType.SWAP) {
			final var stack = player.getInventory().getItem(dragType);
			final var currentItem = Inventory.isHotbarSlot(selectedSlot) ? player.getInventory().items.get(selectedSlot) : selectedSlot == -1 ? player.getInventory().offhand.get(0) : ItemStack.EMPTY;
			
			if (!currentItem.isEmpty() && stack == currentItem) {
				// return ItemStack.EMPTY; // TODO right?
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
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		var itemstack = ItemStack.EMPTY;
		final var slot = slots.get(index);
		
		if (slot != null && slot.hasItem()) {
			final var itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			
			if (index < 1) {
				return ItemStack.EMPTY;
			} else {
				final var stack = itemstack1.copy();
				stack.setCount(1);
				filterItemSlotInventory.setItem(0, stack);
				return ItemStack.EMPTY;
			}
		}
		return itemstack;
	}
	
}
