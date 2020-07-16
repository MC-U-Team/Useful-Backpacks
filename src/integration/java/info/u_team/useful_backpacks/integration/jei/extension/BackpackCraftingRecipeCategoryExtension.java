package info.u_team.useful_backpacks.integration.jei.extension;

import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICustomCraftingCategoryExtension;

public class BackpackCraftingRecipeCategoryExtension implements ICustomCraftingCategoryExtension {
	
	private final BackpackCraftingRecipe recipe;
	
	public BackpackCraftingRecipeCategoryExtension(BackpackCraftingRecipe recipe) {
		this.recipe = recipe;
	}
	
	@Override
	public void setIngredients(IIngredients ingredients) {
		
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IIngredients ingredients) {
	}
	
}
