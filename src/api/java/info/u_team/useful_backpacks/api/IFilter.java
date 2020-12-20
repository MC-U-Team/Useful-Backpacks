package info.u_team.useful_backpacks.api;

import net.minecraft.item.ItemStack;

public interface IFilter {
	
	boolean matchItem(ItemStack filterStack, ItemStack matchStack);
	
	boolean isSetuped(ItemStack filterStack);
	
}
