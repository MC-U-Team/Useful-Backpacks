package info.u_team.useful_backpacks.integration.curios.init;

import info.u_team.useful_backpacks.init.UsefulBackpacksItems;
import info.u_team.useful_backpacks.integration.curios.render.CuriosBackpackRenderer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public class CuriosIntegrationRenderers {
	
	private static void setup(FMLClientSetupEvent event) {
		CuriosRendererRegistry.register(UsefulBackpacksItems.SMALL_BACKPACK.get(), CuriosBackpackRenderer::new);
		CuriosRendererRegistry.register(UsefulBackpacksItems.MEDIUM_BACKPACK.get(), CuriosBackpackRenderer::new);
		CuriosRendererRegistry.register(UsefulBackpacksItems.LARGE_BACKPACK.get(), CuriosBackpackRenderer::new);
		CuriosRendererRegistry.register(UsefulBackpacksItems.ENDERCHEST_BACKPACK.get(), CuriosBackpackRenderer::new);
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(CuriosIntegrationRenderers::setup);
	}
	
}
