package info.u_team.useful_backpacks.container.slot;

import info.u_team.useful_backpacks.api.IBackpack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class BackpackFilterSlot extends Slot {
	
	private final IInventory filterSlotInventory;
	
	public BackpackFilterSlot(IInventory filterSlotInventory, IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.filterSlotInventory = filterSlotInventory;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IBackpack;
	}
	
	@Override
	public ItemStack onTake(PlayerEntity player, ItemStack stack) {
		filterSlotInventory.clear();
		return super.onTake(player, stack);
	}
	
}
