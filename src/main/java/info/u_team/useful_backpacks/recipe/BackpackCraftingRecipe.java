package info.u_team.useful_backpacks.recipe;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

import info.u_team.u_team_core.api.dye.DyeableItem;
import info.u_team.u_team_core.recipeserializer.UShapedRecipeSerializer;
import info.u_team.u_team_core.util.ColorUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksRecipeSerializers;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class BackpackCraftingRecipe extends ShapedRecipe {
	
	public BackpackCraftingRecipe(ResourceLocation id, String group, CraftingBookCategory category, int width, int height, NonNullList<Ingredient> ingredients, ItemStack output) {
		super(id, group, category, width, height, ingredients, output);
	}
	
	@Override
	public ItemStack assemble(CraftingContainer inventory) {
		final ItemStack backpackItem = super.assemble(inventory);
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
		public BackpackCraftingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			@SuppressWarnings("deprecation")
			final CraftingBookCategory category = CraftingBookCategory.CODEC.byName(GsonHelper.getAsString(json, "category", null), CraftingBookCategory.MISC);
			final Map<String, Ingredient> keys = deserializeKey(GsonHelper.getAsJsonObject(json, "key"));
			final String[] pattern = shrink(patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
			final int width = pattern[0].length();
			final int height = pattern.length;
			final NonNullList<Ingredient> ingredients = deserializeIngredients(pattern, keys, width, height);
			final ItemStack output = itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			return new BackpackCraftingRecipe(recipeId, group, category, width, height, ingredients, output);
		}
		
		@Override
		public BackpackCraftingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			final int width = buffer.readVarInt();
			final int height = buffer.readVarInt();
			final String group = buffer.readUtf();
			final CraftingBookCategory category = buffer.readEnum(CraftingBookCategory.class);
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);
			
			for (int index = 0; index < ingredients.size(); ++index) {
				ingredients.set(index, Ingredient.fromNetwork(buffer));
			}
			
			final ItemStack output = buffer.readItem();
			return new BackpackCraftingRecipe(recipeId, group, category, width, height, ingredients, output);
		}
		
		@Override
		public void toNetwork(FriendlyByteBuf buffer, BackpackCraftingRecipe recipe) {
			buffer.writeVarInt(recipe.getWidth());
			buffer.writeVarInt(recipe.getHeight());
			buffer.writeUtf(recipe.getGroup());
			buffer.writeEnum(recipe.category());
			
			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}
			
			buffer.writeItem(recipe.getResultItem());
		}
	}
	
}
