package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.api.IFilter;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemHandlerHelper;

public class FilterItem extends UItem implements IFilter {
	
	public FilterItem() {
		super(UsefulBackpacksItemGroups.GROUP, new Properties().maxStackSize(1));
	}
	
	@Override
	public boolean matchItem(ItemStack filterStack, ItemStack matchStack) {
		if (!(filterStack.getItem() instanceof FilterItem)) {
			return false;
		}
		if (!filterStack.hasTag()) {
			return false;
		}
		final CompoundNBT compound = filterStack.getTag();
		
		if (compound.contains("item")) {
			final boolean strict = compound.getBoolean("strict");
			final ItemStack stack = ItemStack.read(compound.getCompound("stack"));
			return matchStack(strict, stack, matchStack);
		} else if (compound.contains("tag")) {
			final ResourceLocation id = ResourceLocation.tryCreate(compound.getString("id"));
			return matchTag(id, matchStack);
		} else {
			return false;
		}
	}
	
	private boolean matchStack(boolean strict, ItemStack stack, ItemStack matchStack) {
		if (strict) {
			return ItemHandlerHelper.canItemStacksStack(stack, matchStack);
		} else {
			return stack.isItemEqual(matchStack);
		}
	}
	
	private boolean matchTag(ResourceLocation id, ItemStack matchStack) {
		if (id != null) {
			return matchStack.getItem().getTags().contains(id);
		} else {
			return false;
		}
	}
	
}
