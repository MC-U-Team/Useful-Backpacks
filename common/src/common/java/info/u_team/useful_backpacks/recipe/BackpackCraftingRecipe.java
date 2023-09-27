package info.u_team.useful_backpacks.recipe;

import java.util.List;

import com.google.common.collect.Lists;

import info.u_team.u_team_core.api.dye.DyeableItem;
import info.u_team.u_team_core.recipeserializer.UShapedRecipeSerializer;
import info.u_team.u_team_core.util.ColorUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksRecipeSerializers;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class BackpackCraftingRecipe extends ShapedRecipe {
	
	public BackpackCraftingRecipe(String group, CraftingBookCategory category, int width, int height, NonNullList<Ingredient> ingredients, ItemStack output, boolean showNotification) {
		super(group, category, width, height, ingredients, output, showNotification);
	}
	
	@Override
	public ItemStack assemble(CraftingContainer inventory, RegistryAccess registryAccess) {
		final ItemStack backpackItem = super.assemble(inventory, registryAccess);
		final List<DyeColor> dyeList = Lists.newArrayList();
		
		boolean backpackPresent = false;
		
		for (int index = 0; index < inventory.getContainerSize(); ++index) {
			final ItemStack slotStack = inventory.getItem(index);
			if (!slotStack.isEmpty()) {
				final Item item = slotStack.getItem();
				if (item instanceof BackpackItem) {
					if (backpackPresent) { // Does not allow multiple backpacks if somebody changed the recipe to be so
						return ItemStack.EMPTY;
					}
					backpackPresent = true;
					if (slotStack.hasTag()) {
						backpackItem.setTag(slotStack.getTag().copy()); // Copy tag from existing one including color and inventory if exist
					}
				} else {
					if (slotStack.is(ItemTags.WOOL)) {
						final DyeColor color = ColorUtil.getColorFromWool(item);
						if (color != null) {
							dyeList.add(color);
						}
					}
				}
			}
		}
		if (!dyeList.isEmpty() && !dyeList.parallelStream().allMatch(color -> color == DyeColor.WHITE)) { // Don't change color if all color is white (neutral element).
			return DyeableItem.colorStack(backpackItem, dyeList);
		}
		return backpackItem;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return UsefulBackpacksRecipeSerializers.BACKPACK.get();
	}
	
	public static class Serializer extends UShapedRecipeSerializer<BackpackCraftingRecipe> {
		
		@Override
		protected BackpackCraftingRecipe createRecipe(String group, CraftingBookCategory category, int recipeWidth, int recipeHeigt, NonNullList<Ingredient> ingredients, ItemStack output, boolean showNotification) {
			return new BackpackCraftingRecipe(group, category, recipeWidth, recipeHeigt, ingredients, output, showNotification);
		}
	}
}
