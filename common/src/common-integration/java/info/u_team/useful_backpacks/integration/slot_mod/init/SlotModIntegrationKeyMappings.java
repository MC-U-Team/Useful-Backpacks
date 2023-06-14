package info.u_team.useful_backpacks.integration.slot_mod.init;

import org.lwjgl.glfw.GLFW;

import info.u_team.u_team_core.api.registry.LazyEntry;
import info.u_team.u_team_core.api.registry.client.KeyMappingRegister;
import net.minecraft.client.KeyMapping;

public class SlotModIntegrationKeyMappings {
	
	public static final KeyMappingRegister KEY_MAPPINGS = KeyMappingRegister.create();
	
	public static final LazyEntry<KeyMapping> OPEN_BACKPACK = KEY_MAPPINGS.register(() -> new KeyMapping("key.usefulbackpacks.slotmodintegration.open_backpack.description", GLFW.GLFW_KEY_V, "key.usefulbackpacks.slotmodintegration.category"));
	
	public static void register() {
		KEY_MAPPINGS.register();
	}
	
}
