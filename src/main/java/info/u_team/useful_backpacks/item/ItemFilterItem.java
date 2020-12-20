package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.api.IFilter;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemFilterItem extends UItem implements IFilter {
	
	public ItemFilterItem() {
		super(UsefulBackpacksItemGroups.GROUP, new Properties().maxStackSize(1));
	}
	
	@Override
	public boolean matchItem(ItemStack filterStack, ItemStack matchStack) {
		if (!(filterStack.getItem() instanceof ItemFilterItem)) {
			return false;
		}
		if (!filterStack.hasTag()) {
			return false;
		}
		final CompoundNBT compound = filterStack.getTag();
		
		if (compound.contains("stack")) {
			final boolean strict = compound.getBoolean("strict");
			final ItemStack stack = ItemStack.read(compound.getCompound("stack"));
			
			if (strict) {
				return ItemHandlerHelper.canItemStacksStack(stack, matchStack);
			} else {
				return stack.isItemEqual(matchStack);
			}
		} else {
			return false;
		}
	}
}
