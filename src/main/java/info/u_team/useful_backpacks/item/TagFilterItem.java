package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.api.IFilter;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class TagFilterItem extends UItem implements IFilter {
	
	public TagFilterItem() {
		super(UsefulBackpacksItemGroups.GROUP, new Properties().maxStackSize(1));
	}
	
	@Override
	public boolean matchItem(ItemStack filterStack, ItemStack matchStack) {
		if (!(filterStack.getItem() instanceof TagFilterItem)) {
			return false;
		}
		if (!filterStack.hasTag()) {
			return false;
		}
		final CompoundNBT compound = filterStack.getTag();
		
		if (compound.contains("id")) {
			final ResourceLocation id = ResourceLocation.tryCreate(compound.getString("id"));
			
			if (id != null) {
				return matchStack.getItem().getTags().contains(id);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
