package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.container.slot.BackpackFilterSlot;
import info.u_team.useful_backpacks.container.slot.FilterSlot;
import info.u_team.useful_backpacks.init.UsefulBackpacksBlocks;
import info.u_team.useful_backpacks.init.UsefulBackpacksMenuTypes;
import info.u_team.useful_backpacks.inventory.DelegateInventory;
import info.u_team.useful_backpacks.inventory.FilterInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class FilterConfiguratorContainer extends UContainer {
	
	private final ContainerLevelAccess worldPos;
	
	private final Container backpackSlotInventory = new SimpleContainer(1) {
		
		@Override
		public void setChanged() {
			super.setChanged();
			slotsChanged(this);
		}
	};
	private final DelegateInventory filterSlotInventory = new DelegateInventory(new SimpleContainer(9));
	
	private Container filterInventory;
	
	// Client
	public FilterConfiguratorContainer(int id, Inventory playerInventory) {
		this(id, playerInventory, ContainerLevelAccess.NULL);
		filterInventory = new SimpleContainer(9);
	}
	
	// Server
	public FilterConfiguratorContainer(int id, Inventory playerInventory, ContainerLevelAccess worldPos) {
		super(UsefulBackpacksMenuTypes.FILTER_CONFIGURATOR.get(), id);
		this.worldPos = worldPos;
		
		appendInventory(backpackSlotInventory, (inventory, index, xPosition, yPosition) -> new BackpackFilterSlot(inventory, index, xPosition, yPosition), 1, 1, 35, 35);
		appendInventory(filterSlotInventory, (inventory, index, xPosition, yPosition) -> new FilterSlot(backpackSlotInventory, inventory, index, xPosition, yPosition), 3, 3, 89, 17);
		appendPlayerInventory(playerInventory, 8, 84);
	}
	
	@Override
	public boolean stillValid(Player player) {
		return stillValid(worldPos, player, UsefulBackpacksBlocks.FILTER_CONFIGURATOR.get());
	}
	
	@Override
	public void removed(Player player) {
		super.removed(player);
		saveFilterInventory();
		worldPos.execute((world, pos) -> clearContainer(player, world, backpackSlotInventory));
	}
	
	@Override
	public void broadcastChanges() {
		final ItemStack oldStack = getInventoryItemStacks().get(0);
		final ItemStack newStack = backpackSlotInventory.getItem(0);
		
		final boolean stackChanged = !ItemStack.matches(oldStack, newStack);
		
		if (stackChanged) {
			if (newStack.getItem() instanceof IBackpack) {
				if (filterInventory instanceof FilterInventory) {
					((FilterInventory) filterInventory).writeItemStack();
				}
				filterInventory = new FilterInventory(backpackSlotInventory.getStackInSlot(0));
				filterSlotInventory.setInventory(filterInventory);
			} else if (newStack.isEmpty() && filterInventory instanceof FilterInventory) {
				filterInventory = null;
				filterSlotInventory.setInventory(null);
			}
		}
		
		saveFilterInventory();
		super.detectAndSendChanges();
	}
	
	private void saveFilterInventory() {
		if (filterInventory instanceof FilterInventory) {
			final FilterInventory inventory = (FilterInventory) filterInventory;
			final ItemStack copy = inventory.getStack().copy();
			inventory.writeItemStack();
			if (!ItemStack.areItemStacksEqual(copy, inventory.getStack())) {
				for (final IContainerListener listener : getListeners()) {
					if (listener instanceof ServerPlayerEntity) {
						final ServerPlayerEntity player = (ServerPlayerEntity) listener;
						player.connection.netManager.sendPacket(new SSetSlotPacket(windowId, 0, inventory.getStack()));
					}
				}
			}
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			final ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (index < 10) {
				if (!this.mergeItemStack(itemstack1, 10, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 10, false)) {
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
}
