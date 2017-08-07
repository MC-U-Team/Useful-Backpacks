package info.u_team.usefulbackpacks.crafting;

import info.u_team.usefulbackpacks.ModMain;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
	
	public ModCrafting() {
		register();
	}
	
	private void register() {
		Item backpack = ModMain.getInstance().getItems().backpack;
		GameRegistry.addRecipe(new ItemStack(backpack, 1, 0), "wlw", "lsl", "wlw", 'w', Blocks.WOOL, 'l', Items.LEATHER, 's', Items.STRING);
		GameRegistry.addRecipe(new ItemStack(backpack, 1, 1), "wbw", "lbl", "wlw", 'w', Blocks.WOOL, 'l', Items.LEATHER, 'b', new ItemStack(backpack, 1, 0));
		GameRegistry.addRecipe(new ItemStack(backpack, 1, 2), "wbw", "lbl", "wlw", 'w', Blocks.WOOL, 'l', Items.LEATHER, 'b', new ItemStack(backpack, 1, 1));
		GameRegistry.addRecipe(new RecipesBackPackDyes());
	}
	
}
