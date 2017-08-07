package info.u_team.usefulbackpacks.proxy;

import info.u_team.usefulbackpacks.ModMain;
import info.u_team.usefulbackpacks.enums.EnumBackPacks;
import info.u_team.usefulbackpacks.item.ItemBackPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
	
	public void registerModels() {
		
		Item backpacks = ModMain.getInstance().getItems().backpack;
		
		for (int i = 0; i < EnumBackPacks.values().length; i++) {
			ResourceLocation location = new ResourceLocation(backpacks.getRegistryName().toString() + "_" + EnumBackPacks.byMetadata(i).getName());
			ModelLoader.setCustomModelResourceLocation(backpacks, i, new ModelResourceLocation(location, "inventory"));
		}
	}
	
	@Override
	public void registerColors() {
		Item backpacks = ModMain.getInstance().getItems().backpack;
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler((ItemBackPack) backpacks, backpacks);
	}
	
}
