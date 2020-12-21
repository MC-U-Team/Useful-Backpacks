package info.u_team.useful_backpacks.container.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ItemFilterSlot extends Slot {
	
	public ItemFilterSlot(IInventory inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
	}
	
	@Override
	public ItemStack onTake(PlayerEntity player, ItemStack stack) {
		return stack;
	}
	
	@Override
	public ItemStack decrStackSize(int amound) {
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
	@Override
	public void putStack(ItemStack is) {
	}
	
	@Override
	public boolean canTakeStack(PlayerEntity player) {
		return false;
	}
}
