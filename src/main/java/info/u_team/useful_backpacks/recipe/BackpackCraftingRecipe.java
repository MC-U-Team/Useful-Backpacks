package info.u_team.useful_backpacks.recipe;

import java.util.Map;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;

public class BackpackCraftingRecipe extends ShapedRecipe {
	
	public BackpackCraftingRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn) {
		super(idIn, groupIn, recipeWidthIn, recipeHeightIn, recipeItemsIn, recipeOutputIn);
	}
	
	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<BackpackCraftingRecipe> {
		
		private static final ResourceLocation NAME = new ResourceLocation("minecraft", "crafting_shaped");
		
		public BackpackCraftingRecipe read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			Map<String, Ingredient> map = ShapedRecipe.deserializeKey(JSONUtils.getJsonObject(json, "key"));
			String[] astring = ShapedRecipe.shrink(ShapedRecipe.patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
			int i = astring[0].length();
			int j = astring.length;
			NonNullList<Ingredient> nonnulllist = ShapedRecipe.deserializeIngredients(astring, map, i, j);
			ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			return new BackpackCraftingRecipe(recipeId, s, i, j, nonnulllist, itemstack);
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
