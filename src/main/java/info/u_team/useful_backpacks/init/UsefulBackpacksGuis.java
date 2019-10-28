package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.gui.BackpackScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class UsefulBackpacksGuis {
	
	@SubscribeEvent
	public static void register(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(UsefulBackpacksContainerTypes.BACKPACK, BackpackScreen::new);
		ScreenManager.registerFactory(UsefulBackpacksContainerTypes.ENDERCHEST_BACKPACK, ChestScreen::new);
	}
}
