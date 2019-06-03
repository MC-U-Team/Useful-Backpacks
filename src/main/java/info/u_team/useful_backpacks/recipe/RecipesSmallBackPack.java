package info.u_team.useful_backpacks.recipe;

import java.util.*;

import info.u_team.useful_backpacks.init.*;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.util.*;

public class RecipesSmallBackPack extends RecipesBasicBackPack {
	
	public RecipesSmallBackPack(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn) {
		super(idIn, groupIn, recipeWidthIn, recipeHeightIn, recipeItemsIn, recipeOutputIn);
	}
	
	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		
		final List<EnumDyeColor> colors = new ArrayList<>();
		
		for (int k = 0; k < inv.getSizeInventory(); ++k) {
			ItemStack slotStack = inv.getStackInSlot(k);
			
			if (!slotStack.isEmpty()) {
				final EnumDyeColor color = net.minecraft.item.EnumDyeColor.getColor(slotStack);
				if (color != null) {
					colors.add(color);
					float[] afloat = color.getColorComponentValues();
					int l1 = (int) (afloat[0] * 255.0F);
					int i2 = (int) (afloat[1] * 255.0F);
					int j2 = (int) (afloat[2] * 255.0F);
					i += Math.max(l1, Math.max(i2, j2));
					aint[0] += l1;
					aint[1] += i2;
					aint[2] += j2;
					++j;
				}
			}
		}
		
		int i1 = aint[0] / j;
		int j1 = aint[1] / j;
		int k1 = aint[2] / j;
		float f3 = (float) i / (float) j;
		float f4 = (float) Math.max(i1, Math.max(j1, k1));
		i1 = (int) ((float) i1 * f3 / f4);
		j1 = (int) ((float) j1 * f3 / f4);
		k1 = (int) ((float) k1 * f3 / f4);
		int k2 = (i1 << 8) + j1;
		k2 = (k2 << 8) + k1;
		
		final ItemStack backpack = getRecipeOutput().copy();
		if (!colors.parallelStream().allMatch(color -> color == EnumDyeColor.WHITE)) { // Remove color if all are white
			UsefulBackPacksItems.small.setColor(backpack, k2);
		}
		return backpack;
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return UsefulBackPacksRecipes.crafting_small_backpack;
	}
}
