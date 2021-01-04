package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.ClientRegistry;
import info.u_team.useful_backpacks.screen.*;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class UsefulBackpacksScreens {
	
	private static void setup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ClientRegistry.registerScreen(UsefulBackpacksContainerTypes.BACKPACK, BackpackScreen::new);
			ClientRegistry.registerScreen(UsefulBackpacksContainerTypes.ENDERCHEST_BACKPACK, ChestScreen::new);
			ClientRegistry.registerScreen(UsefulBackpacksContainerTypes.FILTER_CONFIGURATOR, FilterConfiguratorScreen::new);
			ClientRegistry.registerScreen(UsefulBackpacksContainerTypes.ITEM_FILTER, ItemFilterScreen::new);
			ClientRegistry.registerScreen(UsefulBackpacksContainerTypes.TAG_FILTER, TagFilterScreen::new);
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulBackpacksScreens::setup);
	}
}
