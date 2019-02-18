package info.u_team.useful_backpacks.recipe;

import java.util.*;
import java.util.Map.Entry;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.*;
import com.google.gson.*;

import info.u_team.useful_backpacks.init.UsefulBackPacksRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.World;

public class RecipeShapedCopyNBT implements IRecipe, net.minecraftforge.common.crafting.IShapedRecipe {
	
	static int MAX_WIDTH = 3;
	static int MAX_HEIGHT = 3;
	
	public static void setCraftingSize(int width, int height) {
		if (MAX_WIDTH < width)
			MAX_WIDTH = width;
		if (MAX_HEIGHT < height)
			MAX_HEIGHT = height;
	}
	
	private final int recipeWidth;
	private final int recipeHeight;
	private final NonNullList<Ingredient> recipeItems;
	private final ItemStack recipeOutput;
	private final ResourceLocation id;
	private final String group;
	
	public RecipeShapedCopyNBT(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn) {
		this.id = idIn;
		this.group = groupIn;
		this.recipeWidth = recipeWidthIn;
		this.recipeHeight = recipeHeightIn;
		this.recipeItems = recipeItemsIn;
		this.recipeOutput = recipeOutputIn;
		
		if (recipeItems.stream().filter(ingredient -> ingredient instanceof IngredientCopyNBT).count() != 1) {
			throw new IllegalArgumentException("In recipe " + id + " must be one usefulbackpacks:copy_nbt ingredient.");
		}
		
	}
	
	public ResourceLocation getId() {
		return this.id;
	}
	
	public IRecipeSerializer<?> getSerializer() {
		return UsefulBackPacksRecipes.CRAFTING_SPECIAL_COPY_NBT;
	}
	
	public String getGroup() {
		return this.group;
	}
	
	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}
	
	public NonNullList<Ingredient> getIngredients() {
		return this.recipeItems;
	}
	
	public boolean canFit(int width, int height) {
		return width >= this.recipeWidth && height >= this.recipeHeight;
	}
	
	public boolean matches(IInventory inv, World worldIn) {
		for (int i = 0; i <= inv.getWidth() - this.recipeWidth; ++i) {
			for (int j = 0; j <= inv.getHeight() - this.recipeHeight; ++j) {
				if (this.checkMatch(inv, i, j, true)) {
					return true;
				}
				
				if (this.checkMatch(inv, i, j, false)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkMatch(IInventory craftingInventory, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
		for (int i = 0; i < craftingInventory.getWidth(); ++i) {
			for (int j = 0; j < craftingInventory.getHeight(); ++j) {
				int k = i - p_77573_2_;
				int l = j - p_77573_3_;
				Ingredient ingredient = Ingredient.EMPTY;
				if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
					if (p_77573_4_) {
						ingredient = this.recipeItems.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
					} else {
						ingredient = this.recipeItems.get(k + l * this.recipeWidth);
					}
				}
				
				if (!ingredient.test(craftingInventory.getStackInSlot(i + j * craftingInventory.getWidth()))) {
					return false;
				}
			}
		}
		return true;
	}
	
	public ItemStack getCraftingResult(IInventory inv) {
		ItemStack nbtstack = getNBTStack(inv);
		NBTTagCompound compound = !nbtstack.isEmpty() ? nbtstack.getTag() : null;
		
		ItemStack stack = this.getRecipeOutput().copy();
		if (compound != null) {
			stack.setTag(compound);
		}
		return stack;
	}
	
	private ItemStack getNBTStack(IInventory inventory) {
		for (int i = 0; i <= inventory.getWidth() - this.recipeWidth; ++i) {
			for (int j = 0; j <= inventory.getHeight() - this.recipeHeight; ++j) {
				ItemStack stack = getIngredientCopy(inventory, i, j, true);
				if (!stack.isEmpty()) {
					return stack;
				}
				ItemStack stack2 = getIngredientCopy(inventory, i, j, false);
				if (!stack2.isEmpty()) {
					return stack;
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private ItemStack getIngredientCopy(IInventory inventory, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
		for (int i = 0; i < inventory.getWidth(); ++i) {
			for (int j = 0; j < inventory.getHeight(); ++j) {
				int k = i - p_77573_2_;
				int l = j - p_77573_3_;
				Ingredient ingredient = Ingredient.EMPTY;
				if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
					if (p_77573_4_) {
						ingredient = this.recipeItems.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
					} else {
						ingredient = this.recipeItems.get(k + l * this.recipeWidth);
					}
				}
				if (ingredient instanceof IngredientCopyNBT) {
					return inventory.getStackInSlot(i + j * inventory.getWidth());
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public int getWidth() {
		return this.recipeWidth;
	}
	
	@Override
	public int getRecipeWidth() {
		return getWidth();
	}
	
	public int getHeight() {
		return this.recipeHeight;
	}
	
	@Override
	public int getRecipeHeight() {
		return getHeight();
	}
	
	private static NonNullList<Ingredient> deserializeIngredients(String[] pattern, Map<String, Ingredient> keys, int patternWidth, int patternHeight) {
		NonNullList<Ingredient> nonnulllist = NonNullList.withSize(patternWidth * patternHeight, Ingredient.EMPTY);
		Set<String> set = Sets.newHashSet(keys.keySet());
		set.remove(" ");
		
		for (int i = 0; i < pattern.length; ++i) {
			for (int j = 0; j < pattern[i].length(); ++j) {
				String s = pattern[i].substring(j, j + 1);
				Ingredient ingredient = keys.get(s);
				if (ingredient == null) {
					throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
				}
				
				set.remove(s);
				nonnulllist.set(j + patternWidth * i, ingredient);
			}
		}
		
		if (!set.isEmpty()) {
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
		} else {
			return nonnulllist;
		}
	}
	
	@VisibleForTesting
	static String[] shrink(String... toShrink) {
		int i = Integer.MAX_VALUE;
		int j = 0;
		int k = 0;
		int l = 0;
		
		for (int i1 = 0; i1 < toShrink.length; ++i1) {
			String s = toShrink[i1];
			i = Math.min(i, firstNonSpace(s));
			int j1 = lastNonSpace(s);
			j = Math.max(j, j1);
			if (j1 < 0) {
				if (k == i1) {
					++k;
				}
				
				++l;
			} else {
				l = 0;
			}
		}
		
		if (toShrink.length == l) {
			return new String[0];
		} else {
			String[] astring = new String[toShrink.length - l - k];
			
			for (int k1 = 0; k1 < astring.length; ++k1) {
				astring[k1] = toShrink[k1 + k].substring(i, j + 1);
			}
			
			return astring;
		}
	}
	
	private static int firstNonSpace(String str) {
		int i;
		for (i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
			;
		}
		
		return i;
	}
	
	private static int lastNonSpace(String str) {
		int i;
		for (i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
			;
		}
		
		return i;
	}
	
	private static String[] patternFromJson(JsonArray jsonArr) {
		String[] astring = new String[jsonArr.size()];
		if (astring.length > MAX_HEIGHT) {
			throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
		} else if (astring.length == 0) {
			throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
		} else {
			for (int i = 0; i < astring.length; ++i) {
				String s = JsonUtils.getString(jsonArr.get(i), "pattern[" + i + "]");
				if (s.length() > MAX_WIDTH) {
					throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
				}
				
				if (i > 0 && astring[0].length() != s.length()) {
					throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
				}
				
				astring[i] = s;
			}
			
			return astring;
		}
	}
	
	private static Map<String, Ingredient> deserializeKey(JsonObject json) {
		Map<String, Ingredient> map = Maps.newHashMap();
		
		for (Entry<String, JsonElement> entry : json.entrySet()) {
			if (entry.getKey().length() != 1) {
				throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
			}
			
			if (" ".equals(entry.getKey())) {
				throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
			}
			
			Ingredient ingredient = Ingredient.fromJson(entry.getValue());
			
			map.put(entry.getKey(), ingredient);
		}
		
		map.put(" ", Ingredient.EMPTY);
		return map;
	}
	
	public static ItemStack deserializeItem(JsonObject p_199798_0_) {
		String s = JsonUtils.getString(p_199798_0_, "item");
		@SuppressWarnings("deprecation")
		Item item = IRegistry.field_212630_s.func_212608_b(new ResourceLocation(s));
		if (item == null) {
			throw new JsonSyntaxException("Unknown item '" + s + "'");
		} else if (p_199798_0_.has("data")) {
			throw new JsonParseException("Disallowed data tag found");
		} else {
			int i = JsonUtils.getInt(p_199798_0_, "count", 1);
			return new ItemStack(item, i);
		}
	}
	
	public static class Serializer implements IRecipeSerializer<RecipeShapedCopyNBT> {
		
		private final ResourceLocation name;
		
		public Serializer(String name) {
			this.name = new ResourceLocation(name);
		}
		
		public RecipeShapedCopyNBT read(ResourceLocation recipeId, JsonObject json) {
			String s = JsonUtils.getString(json, "group", "");
			Map<String, Ingredient> map = RecipeShapedCopyNBT.deserializeKey(JsonUtils.getJsonObject(json, "key"));
			String[] astring = RecipeShapedCopyNBT.shrink(RecipeShapedCopyNBT.patternFromJson(JsonUtils.getJsonArray(json, "pattern")));
			int i = astring[0].length();
			int j = astring.length;
			NonNullList<Ingredient> nonnulllist = RecipeShapedCopyNBT.deserializeIngredients(astring, map, i, j);
			ItemStack itemstack = RecipeShapedCopyNBT.deserializeItem(JsonUtils.getJsonObject(json, "result"));
			return new RecipeShapedCopyNBT(recipeId, s, i, j, nonnulllist, itemstack);
		}
		
		@Override
		public ResourceLocation getName() {
			return name;
		}
		
		public RecipeShapedCopyNBT read(ResourceLocation recipeId, PacketBuffer buffer) {
			int i = buffer.readVarInt();
			int j = buffer.readVarInt();
			String s = buffer.readString(32767);
			NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i * j, Ingredient.EMPTY);
			
			for (int k = 0; k < nonnulllist.size(); ++k) {
				Ingredient ingredient = Ingredient.fromBuffer(buffer);
				nonnulllist.set(k, ingredient);
			}
			
			ItemStack itemstack = buffer.readItemStack();
			return new RecipeShapedCopyNBT(recipeId, s, i, j, nonnulllist, itemstack);
		}
		
		public void write(PacketBuffer buffer, RecipeShapedCopyNBT recipe) {
			buffer.writeVarInt(recipe.recipeWidth);
			buffer.writeVarInt(recipe.recipeHeight);
			buffer.writeString(recipe.group);
			
			for (Ingredient ingredient : recipe.recipeItems) {
				ingredient.writeToBuffer(buffer);
			}
			
			buffer.writeItemStack(recipe.recipeOutput);
		}
	}
}