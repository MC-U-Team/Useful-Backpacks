package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.event.RegisterMenuScreensEvent;
import info.u_team.useful_backpacks.screen.BackpackScreen;
import info.u_team.useful_backpacks.screen.FilterConfiguratorScreen;
import info.u_team.useful_backpacks.screen.ItemFilterScreen;
import info.u_team.useful_backpacks.screen.TagFilterScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulBackpacksScreens {
	
	private static void register(RegisterMenuScreensEvent event) {
		event.registerScreen(UsefulBackpacksMenuTypes.BACKPACK, BackpackScreen::new);
		event.registerScreen(UsefulBackpacksMenuTypes.ENDERCHEST_BACKPACK, ContainerScreen::new);
		event.registerScreen(UsefulBackpacksMenuTypes.FILTER_CONFIGURATOR, FilterConfiguratorScreen::new);
		event.registerScreen(UsefulBackpacksMenuTypes.ITEM_FILTER, ItemFilterScreen::new);
		event.registerScreen(UsefulBackpacksMenuTypes.TAG_FILTER, TagFilterScreen::new);
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulBackpacksScreens::register);
	}
}
