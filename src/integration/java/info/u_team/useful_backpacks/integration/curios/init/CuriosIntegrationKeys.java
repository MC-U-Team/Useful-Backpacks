package info.u_team.useful_backpacks.integration.curios.init;

import org.lwjgl.glfw.GLFW;

import info.u_team.u_team_core.util.registry.ClientRegistry;
import info.u_team.useful_backpacks.integration.curios.network.OpenBackpackMessage;
import info.u_team.useful_backpacks.integration.curios.util.BackpackCuriosUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.event.TickEvent.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CuriosIntegrationKeys {
	
	public static final KeyBinding OPEN_BACKPACK = new KeyBinding("key.usefulbackpacks.curiosintegration.description", GLFW.GLFW_KEY_V, "key.usefulbackpacks.curiosintegration.category");
	
	private static void setup(FMLClientSetupEvent event) {
		OPEN_BACKPACK.setKeyConflictContext(new IKeyConflictContext() {
			
			@Override
			public boolean isActive() {
				return BackpackCuriosUtil.getBackpack(Minecraft.getInstance().player).isPresent();
			}
			
			@Override
			public boolean conflicts(IKeyConflictContext other) {
				return false;
			}
		});
		ClientRegistry.registerKeybinding(OPEN_BACKPACK);
	}
	
	public static void onClientTick(ClientTickEvent event) {
		if (event.phase != Phase.END) {
			return;
		}
		final Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.isGameFocused() && minecraft.currentScreen == null && OPEN_BACKPACK.isKeyDown()) {
			CuriosIntegrationNetwork.NETWORK.sendToServer(new OpenBackpackMessage());
		}
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(CuriosIntegrationKeys::setup);
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(CuriosIntegrationKeys::onClientTick);
	}
}
