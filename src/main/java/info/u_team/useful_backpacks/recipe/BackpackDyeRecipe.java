package info.u_team.useful_backpacks.recipe;

import java.util.List;

import com.google.common.collect.Lists;

import info.u_team.useful_backpacks.item.IDyeableItem;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BackpackDyeRecipe extends SpecialRecipe {
	
	public BackpackDyeRecipe(ResourceLocation location) {
		super(location);
	}
	
	@Override
	public boolean matches(CraftingInventory inventory, World world) {
		ItemStack dyeableItem = ItemStack.EMPTY;
		final List<ItemStack> dyeList = Lists.newArrayList();
		
		for (int index = 0; index < inventory.getSizeInventory(); ++index) {
			final ItemStack slotStack = inventory.getStackInSlot(index);
			if (!slotStack.isEmpty()) {
				final Item item = slotStack.getItem();
				if (item instanceof IDyeableItem) {
					if (!dyeableItem.isEmpty()) {
						return false;
					}
					dyeableItem = slotStack;
				} else {
					if (!(item instanceof DyeItem)) {
						return false;
					}
					dyeList.add(slotStack);
				}
			}
		}
		
		return !dyeableItem.isEmpty() && !dyeList.isEmpty();
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
	public boolean canFit(int width, int height) {
		return width * height >= 2;
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return IRecipeSerializer.CRAFTING_SPECIAL_ARMORDYE;
	}
}