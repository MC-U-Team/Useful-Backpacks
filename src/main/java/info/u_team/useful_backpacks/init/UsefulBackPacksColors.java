package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class UsefulBackPacksColors {
	
	private static ItemColors itemcolors;
	
	public static void init() {
		itemcolors = Minecraft.getMinecraft().getItemColors();
		item();
	}
	
	private static void item() {
		final ItemBackPack backpacks = UsefulBackPacksItems.backpack;
		itemcolors.registerItemColorHandler((stack, index) -> {
			return backpacks.getColor(stack);
		}, backpacks);
	}
}
