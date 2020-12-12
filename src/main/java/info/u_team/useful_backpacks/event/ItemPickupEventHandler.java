package info.u_team.useful_backpacks.event;

import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ItemPickupEventHandler {
	
	private static void onEntityItemPickup(EntityItemPickupEvent event) {
		
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(ItemPickupEventHandler::onEntityItemPickup);
	}
	
}
