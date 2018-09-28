package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.UsefulBackPacksConstants;
import info.u_team.useful_backpacks.crafting.RecipesBackPackDyes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class UsefulBackPacksRecipes {
	
	public static void init() {
		furnace();
		crafting();
	}
	
	private static void crafting() {
		registerCrafting("recipespackpackdyes", new RecipesBackPackDyes());
	}
	
	private static void furnace() {
	}
	
	private static void registerCrafting(String name, IRecipe recipe) {
		ForgeRegistries.RECIPES.register(recipe.setRegistryName(new ResourceLocation(UsefulBackPacksConstants.MODID, name)));
	}
}
