package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.*;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.screen.BackpackScreen;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class UsefulBackpacksScreens {
	
	@SubscribeEvent
	public static void register(FMLClientSetupEvent event) {
		MainThreadWorker.run(() -> {
			ClientRegistry.registryScreen(UsefulBackpacksContainerTypes.BACKPACK, BackpackScreen::new);
			ClientRegistry.registryScreen(UsefulBackpacksContainerTypes.ENDERCHEST_BACKPACK, ChestScreen::new);
		});
	}
}
