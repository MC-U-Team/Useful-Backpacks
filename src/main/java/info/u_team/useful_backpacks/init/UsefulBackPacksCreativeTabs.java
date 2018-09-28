package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.creativetab.UCreativeTab;
import info.u_team.useful_backpacks.UsefulBackPacksConstants;

public class UsefulBackPacksCreativeTabs {
	
	public static final UCreativeTab tab = new UCreativeTab(UsefulBackPacksConstants.MODID, "tab");
	
	public static void init() {
		tab.setIcon(UsefulBackPacksItems.backpack);
	}
	
}
