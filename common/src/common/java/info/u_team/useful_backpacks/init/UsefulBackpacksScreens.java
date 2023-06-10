package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.registry.client.MenuScreenRegister;
import info.u_team.useful_backpacks.screen.BackpackScreen;
import info.u_team.useful_backpacks.screen.FilterConfiguratorScreen;
import info.u_team.useful_backpacks.screen.ItemFilterScreen;
import info.u_team.useful_backpacks.screen.TagFilterScreen;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;

public class UsefulBackpacksScreens {
	
	private static final MenuScreenRegister MENU_SCREENS = Util.make(MenuScreenRegister.create(), menuScreens -> {
		menuScreens.register(UsefulBackpacksMenuTypes.BACKPACK, BackpackScreen::new);
		menuScreens.register(UsefulBackpacksMenuTypes.ENDERCHEST_BACKPACK, ContainerScreen::new);
		menuScreens.register(UsefulBackpacksMenuTypes.FILTER_CONFIGURATOR, FilterConfiguratorScreen::new);
		menuScreens.register(UsefulBackpacksMenuTypes.ITEM_FILTER, ItemFilterScreen::new);
		menuScreens.register(UsefulBackpacksMenuTypes.TAG_FILTER, TagFilterScreen::new);
	});
	
	static void register() {
		MENU_SCREENS.register();
	}
	
}
