package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.UsefulBackPacksConstants;
import info.u_team.useful_backpacks.crafting.RecipesBackPackDyes;
import info.u_team.useful_backpacks.crafting.RecipesCopyBackPack;
import info.u_team.useful_backpacks.crafting.RecipesSmallBackPack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class UsefulBackPacksRecipes {
	
	public static void init() {
		crafting();
	}
	
	private static void crafting() {
		registerCrafting("recipespackpackdyes", new RecipesBackPackDyes());
		registerCrafting("backpack_small", new RecipesSmallBackPack());
		registerCrafting("backpack_medium", new RecipesCopyBackPack(false));
		registerCrafting("backpack_large", new RecipesCopyBackPack(true));
	}
	
	private static void registerCrafting(String name, IRecipe recipe) {
		ForgeRegistries.RECIPES.register(recipe.setRegistryName(new ResourceLocation(UsefulBackPacksConstants.MODID, name)));
	}
}
