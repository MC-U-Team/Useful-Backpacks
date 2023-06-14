package info.u_team.useful_backpacks.integration.slot_mod.init;

import info.u_team.u_team_core.api.event.ClientEvents;
import info.u_team.useful_backpacks.integration.slot_mod.message.OpenBackpackMessage;
import net.minecraft.client.Minecraft;

public class SlotModIntegrationClientEvents {
	
	private static void onEndClientTick(Minecraft minecraft) {
		while (SlotModIntegrationKeyMappings.OPEN_BACKPACK.get().consumeClick()) {
			if (minecraft.screen == null) {
				SlotModIntegrationNetwork.NETWORK.sendToServer(new OpenBackpackMessage());
			}
		}
	}
	
	public static void register() {
		ClientEvents.registerEndClientTick(SlotModIntegrationClientEvents::onEndClientTick);
	}
	
}
