package info.u_team.useful_backpacks.integration.curios.init;

import org.lwjgl.glfw.GLFW;

import info.u_team.u_team_core.util.registry.ClientRegistry;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class CuriosIntegrationKeys {
	
	public static final KeyBinding OPEN_BACKPACK = new KeyBinding("key.usefulbackpacks.curiosintegration.description", GLFW.GLFW_KEY_B, "key.usefulbackpacks.curiosintegration.category");
	
	static {
		OPEN_BACKPACK.setKeyConflictContext(new IKeyConflictContext() {
			
			@Override
			public boolean isActive() {
				return false;
			}
			
			@Override
			public boolean conflicts(IKeyConflictContext other) {
				return false;
			}
		});
	}
	
	@SubscribeEvent
	public static void register(FMLClientSetupEvent event) {
		ClientRegistry.registerKeybinding(OPEN_BACKPACK);
	}
	
}
