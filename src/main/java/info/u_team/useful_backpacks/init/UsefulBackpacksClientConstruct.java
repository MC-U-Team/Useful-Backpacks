package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.IModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;

@Construct(modid = UsefulBackpacksMod.MODID, client = true)
public class UsefulBackpacksClientConstruct implements IModConstruct {
	
	@Override
	public void construct() {
		BusRegister.registerMod(UsefulBackpacksModels::registerMod);
		BusRegister.registerMod(UsefulBackpacksScreens::registerMod);
	}
	
}
