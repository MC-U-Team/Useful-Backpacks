package info.u_team.useful_backpacks.integration.curios.init;

import org.lwjgl.glfw.GLFW;

import info.u_team.useful_backpacks.integration.curios.network.OpenBackpackMessage;
import info.u_team.useful_backpacks.integration.curios.util.BackpackCuriosUtil;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.IEventBus;

public class CuriosIntegrationKeys {
	
	public static final KeyMapping OPEN_BACKPACK = new KeyMapping("key.usefulbackpacks.curiosintegration.description", GLFW.GLFW_KEY_V, "key.usefulbackpacks.curiosintegration.category");
	
	private static void register(RegisterKeyMappingsEvent event) {
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
		event.register(OPEN_BACKPACK);
	}
	
	public static void onClientTick(ClientTickEvent event) {
		if (event.phase != Phase.END) {
			return;
		}
		final Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.isWindowActive() && minecraft.screen == null && OPEN_BACKPACK.isDown()) {
			CuriosIntegrationNetwork.NETWORK.sendToServer(new OpenBackpackMessage());
		}
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(CuriosIntegrationKeys::register);
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(CuriosIntegrationKeys::onClientTick);
	}
}
