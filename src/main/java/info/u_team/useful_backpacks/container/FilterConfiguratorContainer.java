package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.menu.UContainerMenu;
import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.container.slot.BackpackFilterSlot;
import info.u_team.useful_backpacks.container.slot.FilterSlot;
import info.u_team.useful_backpacks.init.UsefulBackpacksBlocks;
import info.u_team.useful_backpacks.init.UsefulBackpacksMenuTypes;
import info.u_team.useful_backpacks.inventory.DelegateInventory;
import info.u_team.useful_backpacks.inventory.FilterInventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FilterConfiguratorContainer extends UContainerMenu {
	
	private final ContainerLevelAccess access;
	
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
	public FilterConfiguratorContainer(int id, Inventory playerInventory, ContainerLevelAccess access) {
		super(UsefulBackpacksMenuTypes.FILTER_CONFIGURATOR.get(), id);
		this.access = access;
		
		addSlots(backpackSlotInventory, (inventory, index, xPosition, yPosition) -> new BackpackFilterSlot(inventory, index, xPosition, yPosition), 1, 1, 35, 35);
		addSlots(filterSlotInventory, (inventory, index, xPosition, yPosition) -> new FilterSlot(backpackSlotInventory, inventory, index, xPosition, yPosition), 3, 3, 89, 17);
		addPlayerInventory(playerInventory, 8, 84);
	}
	
	@Override
	public boolean stillValid(Player player) {
		return stillValid(access, player, UsefulBackpacksBlocks.FILTER_CONFIGURATOR.get());
	}
	
	@Override
	public void removed(Player player) {
		super.removed(player);
		saveFilterInventory();
		access.execute((world, pos) -> clearContainer(player, backpackSlotInventory));
	}
	
	// TODO rewrite this stuff, Can be probably way easier with the new sync / inventory management system
	@Override
	public void broadcastChanges() {
		final ItemStack oldStack = getItems().get(0); // TODO access to remote slot or direct slot content??
		final ItemStack newStack = backpackSlotInventory.getItem(0);
		
		final boolean stackChanged = !ItemStack.matches(oldStack, newStack);
		
		if (stackChanged) {
			if (newStack.getItem() instanceof IBackpack) {
				if (filterInventory instanceof FilterInventory) {
					((FilterInventory) filterInventory).writeItemStack();
				}
				filterInventory = new FilterInventory(backpackSlotInventory.getItem(0));
				filterSlotInventory.setInventory(filterInventory);
			} else if (newStack.isEmpty() && filterInventory instanceof FilterInventory) {
				filterInventory = null;
				filterSlotInventory.setInventory(null);
			}
		}
		
		saveFilterInventory();
		super.broadcastChanges();
	}
	
	private void saveFilterInventory() {
		// TODO fix
		// if (filterInventory instanceof FilterInventory) {
		// final FilterInventory inventory = (FilterInventory) filterInventory;
		// final ItemStack copy = inventory.getStack().copy();
		// inventory.writeItemStack();
		// if (!ItemStack.matches(copy, inventory.getStack())) {
		// for (final IContainerListener listener : getListeners()) {
		// if (listener instanceof ServerPlayerEntity) {
		// final ServerPlayerEntity player = (ServerPlayerEntity) listener;
		// player.connection.netManager.sendPacket(new SSetSlotPacket(windowId, 0, inventory.getStack()));
		// }
		// }
		// }
		// }
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = slots.get(index);
		
		if (slot != null && slot.hasItem()) {
			final ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			
			if (index < 10) {
				if (!this.moveItemStackTo(itemstack1, 10, slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, 10, false)) {
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
