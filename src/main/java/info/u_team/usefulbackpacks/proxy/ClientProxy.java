package info.u_team.usefulbackpacks.proxy;

import info.u_team.usefulbackpacks.enums.EnumBackPacks;
import info.u_team.usefulbackpacks.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	public void preinit(FMLPreInitializationEvent event) {
		super.preinit(event);
		registerModels();
	}
	
	public void init(FMLInitializationEvent event) {
		super.init(event);
		registerItemColors();
	}
	
	public void postinit(FMLPostInitializationEvent event) {
		super.postinit(event);
	}
	
	private void registerModels() {
		ItemBackPack backpacks = UsefulBackPacksItems.backpack;
		for (int i = 0; i < EnumBackPacks.values().length; i++) {
			ResourceLocation location = new ResourceLocation(backpacks.getRegistryName().toString() + "_" + EnumBackPacks.byMetadata(i).getName());
			ModelLoader.setCustomModelResourceLocation(backpacks, i, new ModelResourceLocation(location, "inventory"));
		}
	}
	
	private void registerItemColors() {
		ItemBackPack backpacks = UsefulBackPacksItems.backpack;
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return backpacks.getColor(stack);
			}
		}, backpacks);
	}
	
}
