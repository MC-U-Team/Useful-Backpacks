package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.container.slot.*;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class UsefulBackpacksModels {
	
	private static void setup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ModelBakery.LOCATIONS_BUILTIN_TEXTURES.add(ForgeHooksClient.getBlockMaterial(BackpackFilterSlot.BACKGROUND));
			ModelBakery.LOCATIONS_BUILTIN_TEXTURES.add(ForgeHooksClient.getBlockMaterial(FilterSlot.BACKGROUND));
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulBackpacksModels::setup);
	}
	
}
