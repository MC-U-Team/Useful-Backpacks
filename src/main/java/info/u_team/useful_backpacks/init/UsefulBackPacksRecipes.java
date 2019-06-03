package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.UsefulBackPacksMod;
import info.u_team.useful_backpacks.recipe.*;
import net.minecraft.item.crafting.*;
import net.minecraft.item.crafting.RecipeSerializers.SimpleSerializer;

public class UsefulBackPacksRecipes {
	
	public static final SimpleSerializer<RecipesBackPackDyes> crafting_special_backpackdye = new SimpleSerializer<>(UsefulBackPacksMod.modid + ":crafting_special_backpackdye", RecipesBackPackDyes::new);
	
	public static final IRecipeSerializer<RecipesSmallBackPack> crafting_small_backpack = new RecipesSmallBackPack.Serializer<>(UsefulBackPacksMod.modid + ":crafting_small_backpack", RecipesSmallBackPack::new);
	public static final IRecipeSerializer<RecipesCopyBackPack> crafting_copy_backpack = new RecipesCopyBackPack.Serializer<>(UsefulBackPacksMod.modid + ":crafting_copy_backpack", RecipesCopyBackPack::new);
	
	public static void construct() {
		RecipeSerializers.register(crafting_special_backpackdye);
		RecipeSerializers.register(crafting_small_backpack);
		RecipeSerializers.register(crafting_copy_backpack);
	}
	
}
