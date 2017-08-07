package info.u_team.usefulbackpacks.item;

import info.u_team.usefulbackpacks.ModMain;
import net.minecraft.item.Item;

public class ModItems {
	
	public Item backpack;
	
	public ModItems() {
		init();
		register();
	}
	
	private void init() {
		backpack = new ItemBackPack();
	}
	
	private void register() {
		ModMain.getCore().getCommonRegistry().registerItem(backpack, "backpack");
	}
	
}
