package info.u_team.useful_backpacks.integration.jei.extension;

import java.util.*;
import java.util.stream.*;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.u_team_core.util.ColorUtil;
import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Size2i;

public class BackpackCraftingRecipeCategoryExtension implements ICraftingCategoryExtension {
	
	private final BackpackCraftingRecipe recipe;
	
	public BackpackCraftingRecipeCategoryExtension(BackpackCraftingRecipe recipe) {
		this.recipe = recipe;
	}
	
	@Override
	public void setIngredients(IIngredients ingredients) {
		System.out.println("YSIJSHZFSKDLFHJSKLDJFHSDLKFHSDKLJFHS");
		ingredients.setInputIngredients(recipe.getIngredients());
		final Optional<Ingredient> oneWoolIngredient = recipe.getIngredients().stream().filter(ingredient -> ingredient.test(new ItemStack(Blocks.BLACK_WOOL))).findAny();
		if (oneWoolIngredient.isPresent()) {
			final ItemStack[] matchingStacks = oneWoolIngredient.get().getMatchingStacks();
			final List<ItemStack> outputs = Stream.of(matchingStacks).map(stack -> {
				final DyeColor color = ColorUtil.getColorFromWool(Block.getBlockFromItem(stack.getItem()));
				if (color != null && color != DyeColor.WHITE) {
					return IDyeableItem.colorStack(recipe.getRecipeOutput().copy(), Arrays.asList(color));
				}
				return recipe.getRecipeOutput();
			}).collect(Collectors.toList());
			
			outputs.stream().filter(stack -> !stack.hasTag()).forEach(stack -> System.out.println("NO STACK: " + stack.serializeNBT()));
			
			ingredients.setOutputLists(VanillaTypes.ITEM, Arrays.asList(outputs));
		} else {
			// ingredients.setOutputLists(VanillaTypes.ITEM, Arrays.asList(Arrays.asList(new ItemStack(Items.ACACIA_BOAT), new
			// ItemStack(Items.ACACIA_DOOR))));
			// ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
		}
		// ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}
	
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getId();
	}
	
	@Override
	public Size2i getSize() {
		return new Size2i(recipe.getRecipeWidth(), recipe.getRecipeHeight());
	}
	
	// @Override
	// public void setRecipe(IRecipeLayout recipeLayout, IIngredients ingredients) {
	// final List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
	// final List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
	// recipeLayout.getItemStacks().set(0, outputs.get(0));
	// // recipeLayout.getItemStacks().set(0, Arrays.asList(new ItemStack(Items.ACACIA_BOAT), new
	// // ItemStack(Items.ACACIA_DOOR)));
	// }
	
}
