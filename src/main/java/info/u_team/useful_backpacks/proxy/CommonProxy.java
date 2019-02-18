package info.u_team.useful_backpacks.proxy;

import info.u_team.useful_backpacks.init.*;

public class CommonProxy implements IModProxy {
	
	@Override
	public void construct() {
		UsefulBackPacksItems.construct();
	}
	
	@Override
	public void setup() {
		UsefulBackPacksItemGroups.setup();
	}
	
	@Override
	public void complete() {
	}
	
}
