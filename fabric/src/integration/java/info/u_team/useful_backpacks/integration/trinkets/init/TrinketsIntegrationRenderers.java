package info.u_team.useful_backpacks.integration.trinkets.init;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import info.u_team.u_team_core.api.event.CommonEvents;
import info.u_team.useful_backpacks.init.UsefulBackpacksItems;
import info.u_team.useful_backpacks.integration.trinkets.render.TrinketsBackpackRenderer;

public class TrinketsIntegrationRenderers {
	
	private static void setup() {
		TrinketRendererRegistry.registerRenderer(UsefulBackpacksItems.SMALL_BACKPACK.get(), new TrinketsBackpackRenderer());
		TrinketRendererRegistry.registerRenderer(UsefulBackpacksItems.MEDIUM_BACKPACK.get(), new TrinketsBackpackRenderer());
		TrinketRendererRegistry.registerRenderer(UsefulBackpacksItems.LARGE_BACKPACK.get(), new TrinketsBackpackRenderer());
		TrinketRendererRegistry.registerRenderer(UsefulBackpacksItems.ENDERCHEST_BACKPACK.get(), new TrinketsBackpackRenderer());
	}
	
	static void register() {
		CommonEvents.registerSetup(TrinketsIntegrationRenderers::setup);
	}
	
}
