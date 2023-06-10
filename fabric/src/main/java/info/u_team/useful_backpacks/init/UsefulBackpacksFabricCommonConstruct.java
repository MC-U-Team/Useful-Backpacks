package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.config.FabricCommonConfig;

@Construct(modid = UsefulBackpacksReference.MODID)
public class UsefulBackpacksFabricCommonConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		FabricCommonConfig.getInstance();
	}
}