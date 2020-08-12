package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.*;
import info.u_team.useful_backpacks.screen.BackpackScreen;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class UsefulBackpacksScreens {
	
	private static void setup(FMLClientSetupEvent event) {
		MainThreadWorker.run(() -> {
			ClientRegistry.registerScreen(UsefulBackpacksContainerTypes.BACKPACK, BackpackScreen::new);
			ClientRegistry.registerScreen(UsefulBackpacksContainerTypes.ENDERCHEST_BACKPACK, ChestScreen::new);
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulBackpacksScreens::setup);
	}
}
