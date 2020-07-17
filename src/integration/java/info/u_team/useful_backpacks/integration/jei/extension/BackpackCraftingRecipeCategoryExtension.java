package info.u_team.useful_backpacks.integration.jei.extension;

import java.util.*;
import java.util.stream.*;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.u_team_core.util.ColorUtil;
import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ingredient.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICustomCraftingCategoryExtension;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Size2i;

public class BackpackCraftingRecipeCategoryExtension implements ICustomCraftingCategoryExtension {
	
	private final BackpackCraftingRecipe recipe;
	
	private final ICraftingGridHelper craftingGridHelper;
	
	public BackpackCraftingRecipeCategoryExtension(BackpackCraftingRecipe recipe) {
		this.recipe = recipe;
		craftingGridHelper = new CraftingGridHelper(1);
	}
	
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getId();
	}
	
	@Override
	public Size2i getSize() {
		return new Size2i(recipe.getRecipeWidth(), recipe.getRecipeHeight());
	}
	
	@Override
	public void setIngredients(IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IIngredients ingredients) {
		final IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		final List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
		final List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
		
		craftingGridHelper.setInputs(guiItemStacks, inputs, recipe.getWidth(), recipe.getHeight());
		
		final ItemStack outputStack = outputs.get(0).get(0);
		
		// Handle when focused on wool to display only that color
		
		final IFocus<?> focus = recipeLayout.getFocus();
		if (focus != null && focus.getValue() instanceof ItemStack) {
			final ItemStack focusStack = (ItemStack) focus.getValue();
			final DyeColor color = ColorUtil.getColorFromWool(Block.getBlockFromItem(focusStack.getItem()));
			if (color != null && outputStack.getItem() instanceof IDyeableItem) {
				if (color == DyeColor.WHITE) {
					guiItemStacks.set(0, outputStack);
				} else {
					guiItemStacks.set(0, IDyeableItem.colorStack(outputStack, Arrays.asList(color)));
				}
				return;
			}
		}
		
		// Handle the color rotation
		
		final Optional<Ingredient> oneWoolIngredient = recipe.getIngredients().stream().filter(ingredient -> ingredient.test(new ItemStack(Blocks.WHITE_WOOL))).findAny();
		if (oneWoolIngredient.isPresent()) {
			final List<ItemStack> outputStacks = Stream.of(oneWoolIngredient.get().getMatchingStacks()).map(stack -> {
				final DyeColor color = ColorUtil.getColorFromWool(Block.getBlockFromItem(stack.getItem()));
				if (color != null && outputStack.getItem() instanceof IDyeableItem && color != DyeColor.WHITE) {
					return IDyeableItem.colorStack(outputStack, Arrays.asList(color));
				}
				return outputStack;
			}).collect(Collectors.toList());
			guiItemStacks.set(0, outputStacks);
		} else {
			guiItemStacks.set(0, outputStack);
		}
	}
}
