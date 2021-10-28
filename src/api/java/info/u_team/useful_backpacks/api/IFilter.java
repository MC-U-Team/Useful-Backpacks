package info.u_team.useful_backpacks.api;

import net.minecraft.world.item.ItemStack;

public interface IFilter {
	
	boolean matchItem(ItemStack filterStack, ItemStack matchStack);
	
	boolean isUsable(ItemStack filterStack);
	
}
