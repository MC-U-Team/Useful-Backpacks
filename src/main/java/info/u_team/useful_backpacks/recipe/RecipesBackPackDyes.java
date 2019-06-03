package info.u_team.useful_backpacks.recipe;

import java.util.List;

import com.google.common.collect.Lists;

import info.u_team.useful_backpacks.init.UsefulBackPacksRecipes;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipesBackPackDyes extends IRecipeHidden {
	
	public RecipesBackPackDyes(ResourceLocation location) {
		super(location);
	}
	
	public boolean matches(IInventory inv, World worldIn) {
		if (!(inv instanceof InventoryCrafting)) {
			return false;
		} else {
			ItemStack itemstack = ItemStack.EMPTY;
			List<ItemStack> list = Lists.newArrayList();
			
			for (int i = 0; i < inv.getSizeInventory(); ++i) {
				ItemStack itemstack1 = inv.getStackInSlot(i);
				if (!itemstack1.isEmpty()) {
					if (itemstack1.getItem() instanceof ItemBackPack) {
						if (!itemstack.isEmpty()) {
							return false;
						}
						
						itemstack = itemstack1;
					} else {
						if (!itemstack1.getItem().isIn(net.minecraftforge.common.Tags.Items.DYES)) {
							return false;
						}
						
						list.add(itemstack1);
					}
				}
			}
			
			return !itemstack.isEmpty() && !list.isEmpty();
		}
	}
	
	public ItemStack getCraftingResult(IInventory inv) {
		ItemStack itemstack = ItemStack.EMPTY;
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		ItemBackPack backpack = null;
		
		for (int k = 0; k < inv.getSizeInventory(); ++k) {
			ItemStack itemstack1 = inv.getStackInSlot(k);
			if (!itemstack1.isEmpty()) {
				Item item = itemstack1.getItem();
				if (item instanceof ItemBackPack) {
					backpack = (ItemBackPack) item;
					if (!itemstack.isEmpty()) {
						return ItemStack.EMPTY;
					}
					
					itemstack = itemstack1.copy();
					itemstack.setCount(1);
					if (backpack.hasColor(itemstack1)) {
						int l = backpack.getColor(itemstack);
						float f = (float) (l >> 16 & 255) / 255.0F;
						float f1 = (float) (l >> 8 & 255) / 255.0F;
						float f2 = (float) (l & 255) / 255.0F;
						i = (int) ((float) i + Math.max(f, Math.max(f1, f2)) * 255.0F);
						aint[0] = (int) ((float) aint[0] + f * 255.0F);
						aint[1] = (int) ((float) aint[1] + f1 * 255.0F);
						aint[2] = (int) ((float) aint[2] + f2 * 255.0F);
						++j;
					}
				} else {
					net.minecraft.item.EnumDyeColor color = net.minecraft.item.EnumDyeColor.getColor(itemstack1);
					if (color == null) {
						return ItemStack.EMPTY;
					}
					
					float[] afloat = color.getColorComponentValues();
					int l1 = (int) (afloat[0] * 255.0F);
					int i2 = (int) (afloat[1] * 255.0F);
					int k2 = (int) (afloat[2] * 255.0F);
					i += Math.max(l1, Math.max(i2, k2));
					aint[0] += l1;
					aint[1] += i2;
					aint[2] += k2;
					++j;
				}
			}
		}
		
		if (backpack == null) {
			return ItemStack.EMPTY;
		} else {
			int i1 = aint[0] / j;
			int j1 = aint[1] / j;
			int k1 = aint[2] / j;
			float f3 = (float) i / (float) j;
			float f4 = (float) Math.max(i1, Math.max(j1, k1));
			i1 = (int) ((float) i1 * f3 / f4);
			j1 = (int) ((float) j1 * f3 / f4);
			k1 = (int) ((float) k1 * f3 / f4);
			int j2 = (i1 << 8) + j1;
			j2 = (j2 << 8) + k1;
			backpack.setColor(itemstack, j2);
			return itemstack;
		}
	}
	
	public boolean canFit(int width, int height) {
		return width * height >= 2;
	}
	
	public IRecipeSerializer<?> getSerializer() {
		return UsefulBackPacksRecipes.crafting_special_backpackdye;
	}
}