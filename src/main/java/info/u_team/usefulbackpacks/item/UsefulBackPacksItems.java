package info.u_team.usefulbackpacks.item;

import info.u_team.u_team_core.creativetab.UCreativeTab;
import info.u_team.usefulbackpacks.creativetab.UsefulBackPacksCreativeTabs;

public class UsefulBackPacksItems {
	
	public static ItemBackPack backpack;
	
	private UCreativeTab tab = UsefulBackPacksCreativeTabs.tab;
	
	public UsefulBackPacksItems() {
		backpack = new ItemBackPack("backpack", tab);
		tab.setIcon(backpack);
	}
	
}
