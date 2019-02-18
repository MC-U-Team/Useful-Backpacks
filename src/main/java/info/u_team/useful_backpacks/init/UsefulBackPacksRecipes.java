package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.UsefulBackPacksMod;
import info.u_team.useful_backpacks.recipe.RecipesBackPackDyes;
import net.minecraft.item.crafting.RecipeSerializers;
import net.minecraft.item.crafting.RecipeSerializers.SimpleSerializer;

public class UsefulBackPacksRecipes {
	
	public static final SimpleSerializer<RecipesBackPackDyes> CRAFTING_SPECIAL_BACKPACKDYE = new SimpleSerializer<>(UsefulBackPacksMod.modid + ":crafting_special_backpackdye", RecipesBackPackDyes::new);
	
	public static void construct() {
		RecipeSerializers.register(CRAFTING_SPECIAL_BACKPACKDYE);
	}
	
}
