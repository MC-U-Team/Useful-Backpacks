package info.u_team.useful_backpacks.container;

import info.u_team.useful_backpacks.api.*;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class FilterSlot extends Slot {
	
	public FilterSlot(IInventory inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IFilter;
	}
	
}
