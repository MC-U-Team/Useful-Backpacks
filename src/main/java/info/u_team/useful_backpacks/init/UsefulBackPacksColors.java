package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.registry.ColorsRegistry;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class UsefulBackPacksColors {
	
	public static void construct() {
		ColorsRegistry.register((itemstack, index) -> {
			if (itemstack.getItem() instanceof ItemBackPack) {
				return ((ItemBackPack) itemstack.getItem()).getColor(itemstack);
			}
			return 0;
		}, UsefulBackPacksItems.small, UsefulBackPacksItems.medium, UsefulBackPacksItems.large);
	}
}
