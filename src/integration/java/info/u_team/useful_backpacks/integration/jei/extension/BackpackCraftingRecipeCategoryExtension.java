package info.u_team.useful_backpacks.integration.jei.extension;

import java.util.*;
import java.util.stream.Stream;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.u_team_core.util.ColorUtil;
import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ingredient.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus.Mode;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICustomCraftingCategoryExtension;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.tags.ItemTags;
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
		
		final List<List<ItemStack>> providedInputs = ingredients.getInputs(VanillaTypes.ITEM);
		final List<List<ItemStack>> providedOutputs = ingredients.getOutputs(VanillaTypes.ITEM);
		
		final List<List<ItemStack>> inputs = new ArrayList<>();
		final List<List<ItemStack>> outputs = new ArrayList<>();
		
		for (int index = 0; index < providedInputs.size(); index++) {
			inputs.add(index, new ArrayList<>(providedInputs.get(index)));
		}
		
		for (int index = 0; index < providedOutputs.size(); index++) {
			outputs.add(index, new ArrayList<>(providedOutputs.get(index)));
		}
		
		if (recipeLayout.getFocus() != null && recipeLayout.getFocus().getValue() instanceof ItemStack && outputs.get(0).get(0).getItem() instanceof IDyeableItem) {
			final ItemStack focusStack = (ItemStack) recipeLayout.getFocus().getValue();
			final Item focusItem = focusStack.getItem();
			final Mode mode = recipeLayout.getFocus().getMode();
			
			if (mode == Mode.INPUT && ItemTags.WOOL.contains(focusItem)) {
				final DyeColor color = ColorUtil.getColorFromWool(Block.getBlockFromItem(focusItem));
				if (color != null && color != DyeColor.WHITE) {
					outputs.get(0).set(0, IDyeableItem.colorStack(outputs.get(0).get(0), Arrays.asList(color)));
				}
			} else if (mode == Mode.OUTPUT && focusItem instanceof IDyeableItem) {
				final IDyeableItem dyeableItem = (IDyeableItem) focusItem;
				
				if (dyeableItem.hasColor(focusStack)) {
					final int focusColor = dyeableItem.getColor(focusStack);
					final Optional<DyeColor> colorMatch = Stream.of(DyeColor.values()).filter(dyeColor -> dyeColor.getMapColor().colorValue == focusColor).findAny();
					if (colorMatch.isPresent()) {
						final DyeColor color = colorMatch.get();
						final Block wool = ColorUtil.getWoolFromColor(color);
						
						for (int index = 0; index < inputs.size(); index++) {
							final List<ItemStack> list = inputs.get(index);
							
							if (list.stream().allMatch(stack -> ItemTags.WOOL.contains(stack.getItem()))) {
								inputs.set(index, Arrays.asList(new ItemStack(wool)));
							}
						}
						
						if (color != DyeColor.WHITE) {
							outputs.get(0).set(0, IDyeableItem.colorStack(outputs.get(0).get(0), Arrays.asList(color)));
						}
					}
				} else {
					for (int index = 0; index < inputs.size(); index++) {
						final List<ItemStack> list = inputs.get(index);
						
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
