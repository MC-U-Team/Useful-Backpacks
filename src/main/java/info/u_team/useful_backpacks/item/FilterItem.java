package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.api.IFilter;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public abstract class FilterItem extends UItem implements IFilter {
	
	public FilterItem() {
		super(UsefulBackpacksItemGroups.GROUP, new Properties().maxStackSize(1));
	}
	
	@Override
	public boolean matchItem(ItemStack filterStack, ItemStack matchStack) {
		if (isUsable(filterStack)) {
			return matchItem(filterStack, matchStack, filterStack.getTag());
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isUsable(ItemStack filterStack) {
		return filterStack.hasTag() && isUsable(filterStack, filterStack.getTag());
	}
	
	protected abstract boolean matchItem(ItemStack filterStack, ItemStack matchStack, CompoundNBT compound);
	
	protected abstract boolean isUsable(ItemStack filterStack, CompoundNBT compound);
	
}
