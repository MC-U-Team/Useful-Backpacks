package info.u_team.useful_backpacks.crafting;

import java.util.*;

import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.init.UsefulBackPacksItems;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.block.Block;
import net.minecraft.init.*;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.*;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.oredict.OreDictionary;

public class RecipesCopyBackPack extends ShapedRecipes {
	
	private static final ShapedPrimer mediumPrimer = CraftingHelper.parseShaped(new Object[] { "WLW", "LBL", "WLW", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'L', Items.LEATHER, 'B', new ItemStack(UsefulBackPacksItems.backpack, 1, EnumBackPacks.SMALL.getMetadata()) });
	private static final ShapedPrimer largePrimer = CraftingHelper.parseShaped(new Object[] { "WLW", "LBL", "WLW", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'L', Items.LEATHER, 'B', new ItemStack(UsefulBackPacksItems.backpack, 1, EnumBackPacks.MEDIUM.getMetadata()) });
	
	public RecipesCopyBackPack(boolean large) {
		super("", large ? largePrimer.width : mediumPrimer.width, large ? largePrimer.height : mediumPrimer.height, large ? largePrimer.input : mediumPrimer.input, new ItemStack(UsefulBackPacksItems.backpack, 1, large ? EnumBackPacks.LARGE.getMetadata() : EnumBackPacks.MEDIUM.getMetadata()));
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		final ItemStack backpack = getRecipeOutput().copy();
		final int[] aint = new int[3];
		int i = 0;
		int j = 0;
		
		final List<EnumDyeColor> colors = new ArrayList<>();
		
		for (int k = 0; k < inv.getSizeInventory(); ++k) {
			final ItemStack slotStack = inv.getStackInSlot(k);
			
			if (!slotStack.isEmpty()) {
				if (slotStack.getItem() instanceof ItemBackPack) {
					
					final NBTTagCompound compound = slotStack.getTagCompound();
					if (compound != null) {
						backpack.setTagCompound(compound.copy());
					}
					
					if (UsefulBackPacksItems.backpack.hasColor(slotStack)) {
						final int l = UsefulBackPacksItems.backpack.getColor(slotStack);
						final float f = (l >> 16 & 255) / 255.0F;
						final float f1 = (l >> 8 & 255) / 255.0F;
						final float f2 = (l & 255) / 255.0F;
						i = (int) (i + Math.max(f, Math.max(f1, f2)) * 255.0F);
						aint[0] = (int) (aint[0] + f * 255.0F);
						aint[1] = (int) (aint[1] + f1 * 255.0F);
						aint[2] = (int) (aint[2] + f2 * 255.0F);
						++j;
					}
				} else if (Block.getBlockFromItem(slotStack.getItem()) == Blocks.WOOL) {
					
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
		
		if (!colors.parallelStream().allMatch(color -> color == EnumDyeColor.WHITE)) { // Remove color if all are white
			UsefulBackPacksItems.backpack.setColor(backpack, k2);
		}
		return backpack;
	}
}
