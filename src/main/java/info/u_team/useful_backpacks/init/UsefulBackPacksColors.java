package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class UsefulBackPacksColors {
	
	public static void complete() {
		ItemColors colors = Minecraft.getInstance().getItemColors();
		
		colors.register((itemstack, tintIndex) -> {
			if (itemstack.getItem() instanceof ItemBackPack) {
				return ((ItemBackPack) itemstack.getItem()).getColor(itemstack);
			}
			return 0;
		}, UsefulBackPacksItems.small, UsefulBackPacksItems.medium, UsefulBackPacksItems.large);
	}
	
}
