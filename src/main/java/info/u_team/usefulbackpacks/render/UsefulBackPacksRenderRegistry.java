package info.u_team.usefulbackpacks.render;

import info.u_team.usefulbackpacks.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class UsefulBackPacksRenderRegistry {
	
	public UsefulBackPacksRenderRegistry() {
		item();
	}
	
	private void item() {
		ItemBackPack backpacks = UsefulBackPacksItems.backpack;
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return backpacks.getColor(stack);
			}
		}, backpacks);
	}
	
}
