package info.u_team.useful_backpacks.proxy;

import info.u_team.u_team_core.api.IModProxy;
import info.u_team.useful_backpacks.init.*;

public class CommonProxy implements IModProxy {
	
	@Override
	public void construct() {
		UsefulBackPacksItems.construct();
		UsefulBackPacksRecipes.construct();
	}
	
	@Override
	public void setup() {
		UsefulBackPacksItemGroups.setup();
	}
	
	@Override
	public void complete() {
	}
	
}
