package info.u_team.usefulbackpacks.crafting;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import info.u_team.usefulbackpacks.item.ItemBackPack;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesBackPackDyes implements IRecipe {
	
	/**
	 * TODO Just Copied {@link RecipesArmorDyes} -> Works well, but code is ugly
	 */
	
	public boolean matches(InventoryCrafting inv, World worldIn) {
		ItemStack itemstack = null;
		List<ItemStack> list = Lists.<ItemStack> newArrayList();
		
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack itemstack1 = inv.getStackInSlot(i);
			
			if (itemstack1 != null) {
				if (itemstack1.getItem() instanceof ItemBackPack) {
					itemstack = itemstack1;
				} else {
					if (itemstack1.getItem() != Items.DYE) {
						return false;
					}
					
					list.add(itemstack1);
				}
			}
		}
		
		return itemstack != null && !list.isEmpty();
	}
	
	@Nullable
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack itemstack = null;
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		ItemBackPack itembackpack = null;
		
		for (int k = 0; k < inv.getSizeInventory(); ++k) {
			ItemStack itemstack1 = inv.getStackInSlot(k);
			
			if (itemstack1 != null) {
				if (itemstack1.getItem() instanceof ItemBackPack) {
					itembackpack = (ItemBackPack) itemstack1.getItem();
					
					itemstack = itemstack1.copy();
					itemstack.stackSize = 1;
					
					if (itembackpack.hasColor(itemstack1)) {
						int l = itembackpack.getColor(itemstack);
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
					if (itemstack1.getItem() != Items.DYE) {
						return null;
					}
					
					float[] afloat = EntitySheep.getDyeRgb(EnumDyeColor.byDyeDamage(itemstack1.getMetadata()));
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
		
		if (itembackpack == null) {
			return null;
		} else {
			int i1 = aint[0] / j;
			int j1 = aint[1] / j;
			int k1 = aint[2] / j;
			float f3 = (float) i / (float) j;
			float f4 = (float) Math.max(i1, Math.max(j1, k1));
			i1 = (int) ((float) i1 * f3 / f4);
			j1 = (int) ((float) j1 * f3 / f4);
			k1 = (int) ((float) k1 * f3 / f4);
			int lvt_12_3_ = (i1 << 8) + j1;
			lvt_12_3_ = (lvt_12_3_ << 8) + k1;
			itembackpack.setColor(itemstack, lvt_12_3_);
			return itemstack;
		}
	}
	
	public int getRecipeSize() {
		return 10;
	}
	
	@Nullable
	public ItemStack getRecipeOutput() {
		return null;
	}
	
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		ItemStack[] aitemstack = new ItemStack[inv.getSizeInventory()];
		
		for (int i = 0; i < aitemstack.length; ++i) {
			ItemStack itemstack = inv.getStackInSlot(i);
			aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
		}
		
		return aitemstack;
	}
}