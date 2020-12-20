package info.u_team.useful_backpacks.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class TagFilterItem extends FilterItem {
	
	@Override
	protected boolean matchItem(ItemStack filterStack, ItemStack matchStack, CompoundNBT compound) {
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
