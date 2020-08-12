package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.*;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;

@Construct(modid = UsefulBackpacksMod.MODID)
public class UsefulBackpacksCommonConstruct implements IModConstruct {
	
	@Override
	public void construct() {
		BusRegister.registerMod(UsefulBackpacksContainerTypes::register);
		BusRegister.registerMod(UsefulBackpacksItems::register);
		BusRegister.registerMod(UsefulBackpacksRecipeSerializers::register);
	}
	
}