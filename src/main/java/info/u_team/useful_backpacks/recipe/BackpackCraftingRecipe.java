package info.u_team.useful_backpacks.recipe;

import java.util.*;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

import info.u_team.useful_backpacks.init.UsefulBackpacksRecipes;
import info.u_team.useful_backpacks.item.IDyeableItem;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;

public class BackpackCraftingRecipe extends ShapedRecipe {
	
	public BackpackCraftingRecipe(ResourceLocation id, String group, int width, int height, NonNullList<Ingredient> ingredients, ItemStack output) {
		super(id, group, width, height, ingredients, output);
	}
	
	@Override
	public ItemStack getCraftingResult(CraftingInventory inventory) {
		ItemStack dyeableItem = ItemStack.EMPTY;
		final List<DyeItem> dyeItemList = Lists.newArrayList();
		
		for (int index = 0; index < inventory.getSizeInventory(); ++index) {
			final ItemStack slotStack = inventory.getStackInSlot(index);
			if (!slotStack.isEmpty()) {
				final Item item = slotStack.getItem();
				if (item instanceof IDyeableItem) {
					if (!dyeableItem.isEmpty()) {
						return ItemStack.EMPTY;
					}
					dyeableItem = slotStack.copy();
				} else {
					if (!(item instanceof DyeItem)) {
						return ItemStack.EMPTY;
					}
					dyeItemList.add((DyeItem) item);
				}
			}
		}
		
		if (!dyeableItem.isEmpty() && !dyeItemList.isEmpty()) {
			return IDyeableItem.colorStack(dyeableItem, dyeItemList);
		} else {
			return ItemStack.EMPTY;
		}
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
