package info.u_team.usefulbackpacks.render;

import info.u_team.u_team_core.registry.ClientRegistry;
import info.u_team.usefulbackpacks.enums.EnumBackPacks;
import info.u_team.usefulbackpacks.item.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class UsefulBackPacksModelRegistry {
	
	public UsefulBackPacksModelRegistry() {
		item();
	}
	
	private void item() {
		ItemBackPack backpacks = UsefulBackPacksItems.backpack;
		for (int i = 0; i < EnumBackPacks.values().length; i++) {
			ResourceLocation location = new ResourceLocation(backpacks.getRegistryName().toString() + "_" + EnumBackPacks.byMetadata(i).getName());
			ClientRegistry.registerModel(backpacks, i, new ModelResourceLocation(location, "inventory"));
		}
	}
	
}
