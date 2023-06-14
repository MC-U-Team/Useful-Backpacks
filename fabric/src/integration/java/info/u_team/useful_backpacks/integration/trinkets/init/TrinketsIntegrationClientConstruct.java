package info.u_team.useful_backpacks.integration.trinkets.init;

import info.u_team.u_team_core.api.integration.Integration;
import info.u_team.u_team_core.api.integration.ModIntegration;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.integration.slot_mod.init.SlotModIntegrationClientEvents;
import info.u_team.useful_backpacks.integration.slot_mod.init.SlotModIntegrationKeyMappings;

@Integration(modid = UsefulBackpacksMod.MODID, integration = "trinkets", client = true)
public class TrinketsIntegrationClientConstruct implements ModIntegration {
	
	@Override
	public void construct() {
		SlotModIntegrationKeyMappings.register();
		SlotModIntegrationClientEvents.register();
		
		TrinketsIntegrationRenderers.register();
	}
	
}
