package info.u_team.useful_backpacks.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemFilterItem extends FilterItem {
	
	@Override
	protected boolean matchItem(ItemStack filterStack, ItemStack matchStack, CompoundNBT compound) {
		final boolean strict = compound.getBoolean("strict");
		final ItemStack stack = ItemStack.read(compound.getCompound("stack"));
		
		if (strict) {
			return ItemHandlerHelper.canItemStacksStack(stack, matchStack);
		} else {
			return stack.isItemEqual(matchStack);
		}
	}
	
	@Override
	public boolean isUsable(ItemStack filterStack, CompoundNBT compound) {
		return filterStack.getItem() instanceof ItemFilterItem && compound.contains("stack");
	}
}
