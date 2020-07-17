package info.u_team.useful_backpacks.integration.jei.recipe;

import java.util.*;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.init.UsefulBackpacksItems;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.util.*;

public final class BackpackDyeRecipeMaker {
	
	private BackpackDyeRecipeMaker() {
	}
	
	public static List<ShapelessRecipe> createBackpackDyeRecipes() {
		final List<ShapelessRecipe> list = new ArrayList<>();
		
		for (BackpackItem item : Arrays.asList(UsefulBackpacksItems.SMALL_BACKPACK.get(), UsefulBackpacksItems.MEDIUM_BACKPACK.get(), UsefulBackpacksItems.LARGE_BACKPACK.get())) {
			
			for (DyeColor backpackColor : DyeColor.values()) {
				final ItemStack inputBackpack = IDyeableItem.colorStack(new ItemStack(item), Arrays.asList(backpackColor));
				for (DyeColor dyeColor : DyeColor.values()) {
					final DyeItem inputDye = DyeItem.getItem(dyeColor);
					final ItemStack outputBackpack = IDyeableItem.colorStack(inputBackpack, Arrays.asList(dyeColor));
					
					final NonNullList<Ingredient> inputList = NonNullList.create();
					// inputList.add(Ingredient.fromStacks(inputBackpack));
					inputList.add(Ingredient.fromItems(item));
					inputList.add(Ingredient.fromItems(inputDye));
					
					final ResourceLocation id = new ResourceLocation(UsefulBackpacksMod.MODID, "jei/backpackdye/" + item.getRegistryName().getPath() + "/" + backpackColor.getString() + "/" + dyeColor.getString());
					
					System.out.println("ID " + id);
					
					final ShapelessRecipe recipe = new ShapelessRecipe(id, "", outputBackpack, inputList);
					
					list.add(recipe);
				}
			}
			
		}
		System.out.println("RETURN LSIT WITH: " + list.size());
		return list;
	}
	
}
