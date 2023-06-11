package info.u_team.useful_backpacks.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

public class ItemPickupEventHandler {
	
	private static void onEntityItemPickup(EntityItemPickupEvent event) {
		final Player player = event.getEntity();
		
		if (!(player instanceof ServerPlayer serverPlayer)) {
			return;
		}
		
		final ItemStack stackToPickup = event.getItem().getItem();
		final ItemStack resultStack = ItemPickupCommonEventHandler.insertInBackpacks(serverPlayer, stackToPickup);
		stackToPickup.setCount(resultStack.getCount());
		
		if (resultStack.isEmpty()) {
			event.setResult(Result.ALLOW);
		}
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(EventPriority.HIGHEST, ItemPickupEventHandler::onEntityItemPickup);
	}
	
}
