package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.api.Filter;
import info.u_team.useful_backpacks.init.UsefulBackpacksCreativeTabs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public abstract class FilterItem extends UItem implements Filter {
	
	public FilterItem() {
		super(UsefulBackpacksCreativeTabs.TAB, new Properties().stacksTo(1));
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
	
	protected abstract boolean matchItem(ItemStack filterStack, ItemStack matchStack, CompoundTag compound);
	
	protected abstract boolean isUsable(ItemStack filterStack, CompoundTag compound);
	
}
