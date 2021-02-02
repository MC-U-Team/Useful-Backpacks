package info.u_team.useful_backpacks.item;

import info.u_team.useful_backpacks.api.*;
import info.u_team.useful_backpacks.inventory.FilterInventory;
import net.minecraft.item.*;

public interface INBTBackpack extends IBackpack {
	
	@Override
	default boolean canAutoPickup(ItemStack stack, ItemStack backpackStack) {
		final FilterInventory filterInventory = new FilterInventory(backpackStack);
		
		for (int index = 0; index < filterInventory.getSizeInventory(); index++) {
			final ItemStack filterStack = filterInventory.getStackInSlot(index);
			final Item filterItem = filterStack.getItem();
			if (filterItem instanceof IFilter) {
				final IFilter filter = (IFilter) filterItem;
				
				if (filter.matchItem(filterStack, stack)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
