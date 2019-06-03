package info.u_team.useful_backpacks.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;

@FunctionalInterface
public interface RecipeFunction<C extends RecipesBasicBackPack> {
	
	public C apply(ResourceLocation id, String group, int recipeWidth, int recipeHeight, NonNullList<Ingredient> recipeItems, ItemStack recipeOutput);
	
}
