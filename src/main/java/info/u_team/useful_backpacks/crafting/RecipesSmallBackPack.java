package info.u_team.useful_backpacks.crafting;

import java.util.*;

import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.init.UsefulBackPacksItems;
import net.minecraft.block.Block;
import net.minecraft.init.*;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.*;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.oredict.OreDictionary;

public class RecipesSmallBackPack extends ShapedRecipes {
	
	private static final ShapedPrimer primer = CraftingHelper.parseShaped(new Object[] { "WLW", "LSL", "WLW", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'L', Items.LEATHER, 'S', Items.STRING });
	
	public RecipesSmallBackPack() {
		super("", primer.width, primer.height, primer.input, new ItemStack(UsefulBackPacksItems.backpack, 1, EnumBackPacks.SMALL.getMetadata()));
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		final int[] aint = new int[3];
		int i = 0;
		int j = 0;
		
		final List<EnumDyeColor> colors = new ArrayList<>();
		
		for (int k = 0; k < inv.getSizeInventory(); ++k) {
			final ItemStack slotStack = inv.getStackInSlot(k);
			
			if (!slotStack.isEmpty()) {
				if (Block.getBlockFromItem(slotStack.getItem()) == Blocks.WOOL) {
					
					final EnumDyeColor color = EnumDyeColor.byMetadata(slotStack.getMetadata());
					colors.add(color);
					final float[] afloat = color.getColorComponentValues();
					final int l1 = (int) (afloat[0] * 255.0F);
					final int i2 = (int) (afloat[1] * 255.0F);
					final int j2 = (int) (afloat[2] * 255.0F);
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
		final float f3 = (float) i / (float) j;
		final float f4 = Math.max(i1, Math.max(j1, k1));
		i1 = (int) (i1 * f3 / f4);
		j1 = (int) (j1 * f3 / f4);
		k1 = (int) (k1 * f3 / f4);
		int k2 = (i1 << 8) + j1;
		k2 = (k2 << 8) + k1;
		
		final ItemStack backpack = getRecipeOutput().copy();
		if (!colors.parallelStream().allMatch(color -> color == EnumDyeColor.WHITE)) { // Remove color if all are white
			UsefulBackPacksItems.backpack.setColor(backpack, k2);
		}
		return backpack;
	}
}
