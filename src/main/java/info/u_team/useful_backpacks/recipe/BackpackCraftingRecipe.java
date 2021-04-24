package info.u_team.useful_backpacks.recipe;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.u_team_core.recipeserializer.UShapedRecipeSerializer;
import info.u_team.u_team_core.util.ColorUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksRecipeSerializers;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.block.Block;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class BackpackCraftingRecipe extends ShapedRecipe {
	
	public BackpackCraftingRecipe(ResourceLocation id, String group, int width, int height, NonNullList<Ingredient> ingredients, ItemStack output) {
		super(id, group, width, height, ingredients, output);
	}
	
	@Override
	public ItemStack getCraftingResult(CraftingInventory inventory) {
		final ItemStack backpackItem = super.getCraftingResult(inventory);
		final List<DyeColor> dyeList = Lists.newArrayList();
		
		boolean backpackPresent = false;
		
		for (int index = 0; index < inventory.getSizeInventory(); ++index) {
			final ItemStack slotStack = inventory.getStackInSlot(index);
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
					if (ItemTags.WOOL.contains(item)) {
						final DyeColor color = ColorUtil.getColorFromWool(Block.getBlockFromItem(item));
						if (color != null) {
							dyeList.add(color);
						}
					}
				}
			}
		}
		if (!dyeList.isEmpty() && !dyeList.parallelStream().allMatch(color -> color == DyeColor.WHITE)) { // Don't change color if all color is white (neutral element).
			return IDyeableItem.colorStack(backpackItem, dyeList);
		}
		return backpackItem;
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return UsefulBackpacksRecipeSerializers.BACKPACK.get();
	}
	
	public static class Serializer extends UShapedRecipeSerializer<BackpackCraftingRecipe> {
		
		@Override
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
		
		@Override
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
		
		@Override
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
