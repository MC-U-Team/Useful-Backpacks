package info.u_team.usefulbackpacks.item;

import static info.u_team.usefulbackpacks.tab.UsefulBackPacksTabs.tab;

public class UsefulBackPacksItems {
	
	public static ItemBackPack backpack;
	
	public UsefulBackPacksItems() {
		backpack = new ItemBackPack("backpack", tab);
		tab.setIcon(backpack);
	}
	
}
