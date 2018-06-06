package info.u_team.usefulbackpacks.crafting;

import info.u_team.usefulbackpacks.item.*;
import net.minecraft.init.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UsefulBackPacksCrafting {
	
	public UsefulBackPacksCrafting() {
		register();
	}
	
	private void register() {
		ItemBackPack backpack = UsefulBackPacksItems.backpack;
		GameRegistry.addRecipe(new ItemStack(backpack, 1, 0), "wlw", "lsl", "wlw", 'w', Blocks.WOOL, 'l', Items.LEATHER, 's', Items.STRING);
		GameRegistry.addRecipe(new ItemStack(backpack, 1, 1), "wbw", "lbl", "wlw", 'w', Blocks.WOOL, 'l', Items.LEATHER, 'b', new ItemStack(backpack, 1, 0));
		GameRegistry.addRecipe(new ItemStack(backpack, 1, 2), "wbw", "lbl", "wlw", 'w', Blocks.WOOL, 'l', Items.LEATHER, 'b', new ItemStack(backpack, 1, 1));
		GameRegistry.addRecipe(new RecipesBackPackDyes());
	}
	
}
