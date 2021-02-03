package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.container.slot.*;
import info.u_team.useful_backpacks.init.*;
import info.u_team.useful_backpacks.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;

public class FilterConfiguratorContainer extends UContainer {
	
	private final IWorldPosCallable worldPos;
	
	private final IInventory backpackSlotInventory = new Inventory(1) {
		
		@Override
		public void markDirty() {
			super.markDirty();
			onCraftMatrixChanged(this);
		}
	};
	private final DelegateInventory filterSlotInventory = new DelegateInventory(new Inventory(9));
	
	private IInventory filterInventory;
	
	// Client
	public FilterConfiguratorContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, IWorldPosCallable.DUMMY);
		filterInventory = new Inventory(9);
	}
	
	// Server
	public FilterConfiguratorContainer(int id, PlayerInventory playerInventory, IWorldPosCallable worldPos) {
		super(UsefulBackpacksContainerTypes.FILTER_CONFIGURATOR.get(), id);
		this.worldPos = worldPos;
		
		appendInventory(backpackSlotInventory, (inventory, index, xPosition, yPosition) -> new BackpackFilterSlot(inventory, index, xPosition, yPosition), 1, 1, 35, 35);
		appendInventory(filterSlotInventory, (inventory, index, xPosition, yPosition) -> new FilterSlot(backpackSlotInventory, inventory, index, xPosition, yPosition), 3, 3, 89, 17);
		appendPlayerInventory(playerInventory, 8, 84);
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(worldPos, player, UsefulBackpacksBlocks.FILTER_CONFIGURATOR.get());
	}
	
	@Override
	public void onContainerClosed(PlayerEntity player) {
		super.onContainerClosed(player);
		saveFilterInventory();
		worldPos.consume((world, pos) -> clearContainer(player, world, backpackSlotInventory));
	}
	
	@Override
	public void detectAndSendChanges() {
		final ItemStack oldStack = getInventoryItemStacks().get(0);
		final ItemStack newStack = backpackSlotInventory.getStackInSlot(0);
		
		final boolean stackChanged = !ItemStack.areItemStacksEqual(oldStack, newStack);
		
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
}
