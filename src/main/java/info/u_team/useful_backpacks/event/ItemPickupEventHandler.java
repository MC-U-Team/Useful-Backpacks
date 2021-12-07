package info.u_team.useful_backpacks.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.config.CommonConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ItemPickupEventHandler {
	
	public static final List<Function<ServerPlayer, ItemStack>> INTEGRATION_BACKPACKS = new ArrayList<>();
	
	private static void onEntityItemPickup(EntityItemPickupEvent event) {
		final var player = event.getPlayer();
		
		if (!(player instanceof ServerPlayer)) {
			return;
		}
		
		final var stackToPickup = event.getItem().getItem();
		if (stackToPickup.getItem() instanceof Backpack && !CommonConfig.getInstance().allowStackingBackpacks.get()) { // TODO Move somewhere else
			return;
		}
		final var resultStack = insertInBackpacks((ServerPlayer) player, stackToPickup);
		stackToPickup.setCount(resultStack.getCount());
		
		if (resultStack.isEmpty()) {
			event.setResult(Result.ALLOW);
		}
		
	}
	
	private static ItemStack insertInBackpacks(ServerPlayer player, ItemStack stackToPickup) {
		final var playerInventory = player.getInventory();
		
		for (final Function<ServerPlayer, ItemStack> function : INTEGRATION_BACKPACKS) {
			final var stack = function.apply(player);
			if (!stack.isEmpty()) {
				stackToPickup = insertInBackpack(player, stack, stackToPickup);
				if (stackToPickup.isEmpty()) {
					return stackToPickup;
				}
			}
		}
		
		for (var index = 0; index < playerInventory.getContainerSize(); index++) {
			final var stack = playerInventory.getItem(index);
			
			stackToPickup = insertInBackpack(player, stack, stackToPickup);
			if (stackToPickup.isEmpty()) {
				return stackToPickup;
			}
		}
		return stackToPickup;
	}
	
	private static ItemStack insertInBackpack(ServerPlayer player, ItemStack stack, ItemStack stackToPickup) {
		final var item = stack.getItem();
		
		if (item instanceof Backpack backpack) {
			if (backpack.canAutoPickup(stackToPickup, stack)) {
				final var inventory = backpack.getInventory(player, stack);
				final IItemHandler itemHandler = new InvWrapper(inventory);
				final var result = ItemHandlerHelper.insertItemStacked(itemHandler, stackToPickup, false);
				if (result.getCount() != stackToPickup.getCount()) {
					backpack.saveInventory(inventory, stack);
				}
				stackToPickup = result;
			}
		}
		return stackToPickup;
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(EventPriority.HIGHEST, ItemPickupEventHandler::onEntityItemPickup);
	}
	
}
