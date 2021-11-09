package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.api.sync.MessageHolder;
import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksMenuTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;

public class TagFilterContainer extends UContainer {
	
	private static final int MAX_TAG_LENGTH = 32767;
	
	private final int selectedSlot;
	private String tag;
	
	private final MessageHolder tagMessage;
	
	public TagFilterContainer(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
		this(id, playerInventory, ItemStack.EMPTY, buffer.readVarInt(), buffer.readUtf(MAX_TAG_LENGTH));
	}
	
	public TagFilterContainer(int id, Inventory playerInventory, ItemStack filterStack, int selectedSlot, String tag) {
		super(UsefulBackpacksMenuTypes.TAG_FILTER.get(), id);
		this.selectedSlot = selectedSlot;
		this.tag = tag;
		
		appendPlayerInventory(playerInventory, 8, 108);
		
		tagMessage = addClientToServerTracker(new MessageHolder(buffer -> {
			final String newTag = buffer.readUtf(MAX_TAG_LENGTH);
			if (!filterStack.isEmpty()) {
				if (newTag.isEmpty()) {
					filterStack.removeTagKey("id");
				} else {
					filterStack.getOrCreateTag().putString("id", newTag);
				}
			}
		}));
	}
	
	@Override
	public ItemStack clicked(int slotId, int dragType, ClickType clickType, Player player) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < slots.size()) {
			tmpSlot = slots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.container == player.inventory && tmpSlot.getSlotIndex() == selectedSlot) {
				return tmpSlot.getItem();
			}
		}
		if (clickType == ClickType.SWAP) {
			final ItemStack stack = player.inventory.getItem(dragType);
			final ItemStack currentItem = Inventory.isHotbarSlot(selectedSlot) ? player.inventory.items.get(selectedSlot) : selectedSlot == -1 ? player.inventory.offhand.get(0) : ItemStack.EMPTY;
			
			if (!currentItem.isEmpty() && stack == currentItem) {
				return ItemStack.EMPTY;
			}
		}
		return super.clicked(slotId, dragType, clickType, player);
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public MessageHolder getTagMessage() {
		return tagMessage;
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = slots.get(index);
		
		if (slot != null && slot.hasItem()) {
			final ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			
			if (index < 27) {
				if (!this.moveItemStackTo(itemstack1, 27, 36, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, 27, false)) {
				return ItemStack.EMPTY;
			}
			
			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return itemstack;
	}
}
