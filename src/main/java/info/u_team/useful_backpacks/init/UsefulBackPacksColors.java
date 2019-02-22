package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.registry.ColorsRegistry;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class UsefulBackPacksColors {
	
	public static void construct() {
		ColorsRegistry.register((itemstack, index) -> {
			Item item = itemstack.getItem();
			if (item instanceof ItemBackPack) {
				return ((ItemBackPack) item).getColor(itemstack);
			}
			return 0;
		}, UsefulBackPacksItems.small, UsefulBackPacksItems.medium, UsefulBackPacksItems.large);
	}
}
