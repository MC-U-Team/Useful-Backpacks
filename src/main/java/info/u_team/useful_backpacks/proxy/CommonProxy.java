package info.u_team.useful_backpacks.proxy;

import info.u_team.useful_backpacks.init.*;

public class CommonProxy {
	
	public static void construct() {
		UsefulBackPacksItems.construct();
	}
	
	public static void setup() {
		UsefulBackPacksItemGroups.setup();
	}
	
}
