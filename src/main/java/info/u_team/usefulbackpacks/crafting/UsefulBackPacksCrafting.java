package info.u_team.usefulbackpacks.crafting;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class UsefulBackPacksCrafting {
	
	public UsefulBackPacksCrafting() {
		register();
	}
	
	private void register() {
		ForgeRegistries.RECIPES.register(new RecipesBackPackDyes());
	}
	
}
