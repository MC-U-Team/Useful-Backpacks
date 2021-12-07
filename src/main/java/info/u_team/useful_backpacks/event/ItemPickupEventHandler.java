package info.u_team.useful_backpacks.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.config.CommonConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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
		final Player player = event.getPlayer();
		
		if (!(player instanceof ServerPlayer)) {
			return;
		}
		
		final ItemStack stackToPickup = event.getItem().getItem();
		if (stackToPickup.getItem() instanceof Backpack && !CommonConfig.getInstance().allowStackingBackpacks.get()) { // TODO Move somewhere else
			return;
		}
		final ItemStack resultStack = insertInBackpacks((ServerPlayer) player, stackToPickup);
		stackToPickup.setCount(resultStack.getCount());
		
		if (resultStack.isEmpty()) {
			event.setResult(Result.ALLOW);
		}
		
	}
	
	private static ItemStack insertInBackpacks(ServerPlayer player, ItemStack stackToPickup) {
		final Inventory playerInventory = player.getInventory();
		
		for (final Function<ServerPlayer, ItemStack> function : INTEGRATION_BACKPACKS) {
			final ItemStack stack = function.apply(player);
			if (!stack.isEmpty()) {
				stackToPickup = insertInBackpack(player, stack, stackToPickup);
				if (stackToPickup.isEmpty()) {
					return stackToPickup;
				}
			}
		}
		
		for (int index = 0; index < playerInventory.getContainerSize(); index++) {
			final ItemStack stack = playerInventory.getItem(index);
			
			stackToPickup = insertInBackpack(player, stack, stackToPickup);
			if (stackToPickup.isEmpty()) {
				return stackToPickup;
			}
		}
		return stackToPickup;
	}
	
	private static ItemStack insertInBackpack(ServerPlayer player, ItemStack stack, ItemStack stackToPickup) {
		final Item item = stack.getItem();
		
		if (item instanceof Backpack) {
			final Backpack backpack = (Backpack) item;
			
			if (backpack.canAutoPickup(stackToPickup, stack)) {
				final Container inventory = backpack.getInventory(player, stack);
				final IItemHandler itemHandler = new InvWrapper(inventory);
				final ItemStack result = ItemHandlerHelper.insertItemStacked(itemHandler, stackToPickup, false);
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
