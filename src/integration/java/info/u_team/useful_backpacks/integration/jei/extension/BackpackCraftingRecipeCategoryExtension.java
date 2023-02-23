package info.u_team.useful_backpacks.integration.jei.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import info.u_team.u_team_core.api.dye.DyeableItem;
import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public class BackpackCraftingRecipeCategoryExtension implements ICraftingCategoryExtension {
	
	private final BackpackCraftingRecipe recipe;
	
	public BackpackCraftingRecipeCategoryExtension(BackpackCraftingRecipe recipe) {
		this.recipe = recipe;
	}
	
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getId();
	}
	
	@Override
	public int getWidth() {
		return recipe.getRecipeWidth();
	}
	
	@Override
	public int getHeight() {
		return recipe.getRecipeHeight();
	}
	
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
		final List<List<ItemStack>> inputs = recipe.getIngredients().stream().map(ingredient -> Lists.newArrayList(ingredient.getItems())).collect(Collectors.toCollection(ArrayList::new));
		final List<ItemStack> outputs = Lists.newArrayList(recipe.getResultItem());
		
		Arrays.stream(DyeColor.values()).map(color -> {
			return DyeableItem.colorStack(outputs.get(0), List.of(color));
		}).forEach(outputs::add);
		
		// final IFocus<ItemStack> focus = focuses.getFocuses(VanillaTypes.ITEM).findFirst().orElse(null);
		// TODO reimplement old matching logic
		
		craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, getWidth(), getHeight());
		craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outputs);
	}
}
