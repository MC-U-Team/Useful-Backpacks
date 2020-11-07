package info.u_team.useful_backpacks.crafting;

import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.init.UsefulBackPacksItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class RecipesSmallBackPack extends ShapedRecipes {
	
	private static final ShapedPrimer primer = CraftingHelper.parseShaped(new Object[] { "WLW", "LSL", "WLW", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'L', Items.LEATHER, 'S', Items.STRING });
	
	public RecipesSmallBackPack() {
		super("", primer.width, primer.height, primer.input, new ItemStack(UsefulBackPacksItems.backpack, 1, EnumBackPacks.SMALL.getMetadata()));
	}
	
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		
		final List<EnumDyeColor> colors = new ArrayList<>();
		
		for (int k = 0; k < inv.getSizeInventory(); ++k) {
			ItemStack slotStack = inv.getStackInSlot(k);
			
			if (!slotStack.isEmpty()) {
				if (Block.getBlockFromItem(slotStack.getItem()) == Blocks.WOOL) {
					
					EnumDyeColor color = EnumDyeColor.byMetadata(slotStack.getMetadata());
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
			UsefulBackPacksItems.backpack.setColor(backpack, k2);
		}
		return backpack;
	}
}
