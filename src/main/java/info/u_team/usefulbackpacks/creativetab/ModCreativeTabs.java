package info.u_team.usefulbackpacks.creativetab;

import info.u_team.usefulbackpacks.ModMain;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModCreativeTabs {
	
	public CreativeTabs usefullbackpacks;
	
	public ModCreativeTabs() {
		init();
	}
	
	private void init() {
		usefullbackpacks = new CreativeTabs("usefullbackpacks") {
			
			@Override
			public Item getTabIconItem() {
				return ModMain.getInstance().getItems().backpack;
			}
		};
	}
	
}
