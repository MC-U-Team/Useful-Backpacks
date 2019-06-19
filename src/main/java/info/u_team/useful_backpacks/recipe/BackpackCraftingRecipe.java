package info.u_team.useful_backpacks.recipe;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

import info.u_team.useful_backpacks.init.UsefulBackpacksRecipes;
import info.u_team.useful_backpacks.item.IDyeableItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class BackpackCraftingRecipe extends ShapedRecipe {

	public BackpackCraftingRecipe(ResourceLocation id, String group, int width, int height,
			NonNullList<Ingredient> ingredients, ItemStack output) {
		super(id, group, width, height, ingredients, output);
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inventory) {
		final ItemStack dyeableItem = super.getCraftingResult(inventory);
		final List<DyeColor> dyeList = Lists.newArrayList();

		boolean isDyeableItemPresent = false;

		for (int index = 0; index < inventory.getSizeInventory(); ++index) {
			final ItemStack slotStack = inventory.getStackInSlot(index);
			if (!slotStack.isEmpty()) {
				final Item item = slotStack.getItem();
				if (item instanceof IDyeableItem) {
					if (isDyeableItemPresent) {
						return ItemStack.EMPTY;
					}
					isDyeableItemPresent = true;
					dyeableItem.setTag(slotStack.getTag().copy());
				} else {
					final DyeColor color = getColorFromWool(item);
					if (color != null) {
						dyeList.add(color);
					}
				}
			}
		}
		return ItemStack.EMPTY;

//		if (!dyeableItem.isEmpty() && !dyeList.isEmpty()) {
//			return IDyeableItem.colorStack(dyeableItem, dyeList);
//		} else {
//			return ItemStack.EMPTY;
//		}
	}

	private DyeColor getColorFromWool(Item item) {
		final Block block = Block.getBlockFromItem(item);
		if (block != Blocks.AIR) {
			if (block == Blocks.WHITE_WOOL) {
				return DyeColor.WHITE;
			} else if (block == Blocks.ORANGE_WOOL) {
				return DyeColor.ORANGE;
			} else if (block == Blocks.MAGENTA_WOOL) {
				return DyeColor.MAGENTA;
			} else if (block == Blocks.LIGHT_BLUE_WOOL) {
				return DyeColor.LIGHT_BLUE;
			} else if (block == Blocks.YELLOW_WOOL) {
				return DyeColor.YELLOW;
			} else if (block == Blocks.LIME_WOOL) {
				return DyeColor.LIME;
			} else if (block == Blocks.PINK_WOOL) {
				return DyeColor.PINK;
			} else if (block == Blocks.GRAY_WOOL) {
				return DyeColor.GRAY;
			} else if (block == Blocks.LIGHT_GRAY_WOOL) {
				return DyeColor.LIGHT_GRAY;
			} else if (block == Blocks.CYAN_WOOL) {
				return DyeColor.CYAN;
			} else if (block == Blocks.PURPLE_WOOL) {
				return DyeColor.PURPLE;
			} else if (block == Blocks.BLUE_WOOL) {
				return DyeColor.BLUE;
			} else if (block == Blocks.BROWN_WOOL) {
				return DyeColor.BROWN;
			} else if (block == Blocks.GREEN_WOOL) {
				return DyeColor.GREEN;
			} else if (block == Blocks.RED_WOOL) {
				return DyeColor.RED;
			} else if (block == Blocks.BLACK_WOOL) {
				return DyeColor.BLACK;
			}
		}
		return null;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return UsefulBackpacksRecipes.backpack;
	}

	public static class Serializer extends URecipeSerializer<BackpackCraftingRecipe> {

		public Serializer(String name) {
			super(name);
		}

		public BackpackCraftingRecipe read(ResourceLocation recipeId, JsonObject json) {
			final String group = JSONUtils.getString(json, "group", "");
			final Map<String, Ingredient> keys = deserializeKey(JSONUtils.getJsonObject(json, "key"));
			final String[] pattern = shrink(patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
			final int width = pattern[0].length();
			final int height = pattern.length;
			final NonNullList<Ingredient> ingredients = deserializeIngredients(pattern, keys, width, height);
			final ItemStack output = deserializeItem(JSONUtils.getJsonObject(json, "result"));
			return new BackpackCraftingRecipe(recipeId, group, width, height, ingredients, output);
		}

		public BackpackCraftingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			final int width = buffer.readVarInt();
			final int height = buffer.readVarInt();
			final String group = buffer.readString(32767);
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);

			for (int index = 0; index < ingredients.size(); ++index) {
				ingredients.set(index, Ingredient.read(buffer));
			}

			final ItemStack output = buffer.readItemStack();
			return new BackpackCraftingRecipe(recipeId, group, width, height, ingredients, output);
		}

		public void write(PacketBuffer buffer, BackpackCraftingRecipe recipe) {
			buffer.writeVarInt(recipe.getWidth());
			buffer.writeVarInt(recipe.getHeight());
			buffer.writeString(recipe.getGroup());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.write(buffer);
			}

			buffer.writeItemStack(recipe.getRecipeOutput());
		}
	}

}
