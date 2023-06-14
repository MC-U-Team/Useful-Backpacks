package info.u_team.useful_backpacks.integration.trinkets.init;

import info.u_team.u_team_core.api.integration.Integration;
import info.u_team.u_team_core.api.integration.ModIntegration;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.integration.slot_mod.init.SlotModIntegrationAddBackpackIntegration;
import info.u_team.useful_backpacks.integration.slot_mod.init.SlotModIntegrationNetwork;

@Integration(modid = UsefulBackpacksMod.MODID, integration = "trinkets")
public class TrinketsIntegrationCommonConstruct implements ModIntegration {
	
	@Override
	public void construct() {
		SlotModIntegrationNetwork.register();
		SlotModIntegrationAddBackpackIntegration.register();
	}
	
}
