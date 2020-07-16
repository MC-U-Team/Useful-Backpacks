package info.u_team.useful_backpacks.integration.jei.extension;

import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICustomCraftingCategoryExtension;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Size2i;

public class BackpackCraftingRecipeCategoryExtension implements ICustomCraftingCategoryExtension {
	
	private final BackpackCraftingRecipe recipe;
	
	public BackpackCraftingRecipeCategoryExtension(BackpackCraftingRecipe recipe) {
		this.recipe = recipe;
	}
	
	@Override
	public void setIngredients(IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IIngredients ingredients) {
	}
	
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getId();
	}
	
	@Override
	public Size2i getSize() {
		return new Size2i(recipe.getRecipeWidth(), recipe.getRecipeHeight());
	}
	
}
