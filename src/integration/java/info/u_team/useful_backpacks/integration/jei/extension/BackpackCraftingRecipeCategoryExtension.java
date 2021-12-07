package info.u_team.useful_backpacks.integration.jei.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import info.u_team.u_team_core.api.dye.DyeableItem;
import info.u_team.u_team_core.util.ColorUtil;
import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus.Mode;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICustomCraftingCategoryExtension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IIngredients ingredients) {
		final var guiItemStacks = recipeLayout.getItemStacks();
		
		final List<List<ItemStack>> providedInputs = ingredients.getInputs(VanillaTypes.ITEM);
		final List<List<ItemStack>> providedOutputs = ingredients.getOutputs(VanillaTypes.ITEM);
		
		final List<List<ItemStack>> inputs = new ArrayList<>();
		final List<List<ItemStack>> outputs = new ArrayList<>();
		
		for (var index = 0; index < providedInputs.size(); index++) {
			inputs.add(index, new ArrayList<>(providedInputs.get(index)));
		}
		
		for (var index = 0; index < providedOutputs.size(); index++) {
			outputs.add(index, new ArrayList<>(providedOutputs.get(index)));
		}
		
		if (recipeLayout.getFocus(VanillaTypes.ITEM) != null && recipeLayout.getFocus(VanillaTypes.ITEM).getValue() instanceof ItemStack && outputs.get(0).get(0).getItem() instanceof DyeableItem) {
			final var focusStack = recipeLayout.getFocus(VanillaTypes.ITEM).getValue();
			final var focusItem = focusStack.getItem();
			final var mode = recipeLayout.getFocus(VanillaTypes.ITEM).getMode();
			
			if (mode == Mode.INPUT && ItemTags.WOOL.contains(focusItem)) {
				final var color = ColorUtil.getColorFromWool(Block.byItem(focusItem));
				if (color != null && color != DyeColor.WHITE) {
					outputs.get(0).set(0, DyeableItem.colorStack(outputs.get(0).get(0), Arrays.asList(color)));
				}
			} else if (mode == Mode.OUTPUT && focusItem instanceof DyeableItem dyeableItem) {
				if (dyeableItem.hasColor(focusStack)) {
					final var focusColor = dyeableItem.getColor(focusStack);
					
					// TODO rework this check
					final var colorMatch = Stream.of(DyeColor.values()).filter(dyeColor -> ((((int) (dyeColor.getTextureDiffuseColors()[0] * 255) & 0x0ff) << 16) | (((int) (dyeColor.getTextureDiffuseColors()[1] * 255) & 0x0ff) << 8) | ((int) (dyeColor.getTextureDiffuseColors()[2] * 255) & 0x0ff)) == focusColor).findAny();
					if (colorMatch.isPresent()) {
						final var color = colorMatch.get();
						final var wool = ColorUtil.getWoolFromColor(color);
						
						for (var index = 0; index < inputs.size(); index++) {
							final var list = inputs.get(index);
							
							if (list.stream().allMatch(stack -> ItemTags.WOOL.contains(stack.getItem()))) {
								inputs.set(index, Arrays.asList(new ItemStack(wool)));
							}
						}
						
						if (color != DyeColor.WHITE) {
							outputs.get(0).set(0, DyeableItem.colorStack(outputs.get(0).get(0), Arrays.asList(color)));
						}
					}
				} else {
					for (var index = 0; index < inputs.size(); index++) {
						final var list = inputs.get(index);
						
						if (list.stream().allMatch(stack -> ItemTags.WOOL.contains(stack.getItem()))) {
							inputs.set(index, Arrays.asList(new ItemStack(Blocks.WHITE_WOOL)));
						}
					}
				}
			}
		}
		
		craftingGridHelper.setInputs(guiItemStacks, inputs, getSize().width, getSize().height);
		guiItemStacks.set(0, outputs.get(0));
	}
}
