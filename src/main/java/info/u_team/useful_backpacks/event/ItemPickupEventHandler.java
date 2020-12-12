package info.u_team.useful_backpacks.event;

import info.u_team.useful_backpacks.api.IBackpack;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.items.*;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ItemPickupEventHandler {
	
	private static void onEntityItemPickup(EntityItemPickupEvent event) {
		final PlayerEntity player = event.getPlayer();
		
		if (!(player instanceof ServerPlayerEntity)) {
			return;
		}
		
		final ItemStack stackToPickup = event.getItem().getItem();
		final ItemStack resultStack = insertInBackpacks((ServerPlayerEntity) player, stackToPickup);
		stackToPickup.setCount(resultStack.getCount());
		
		if (resultStack.isEmpty()) {
			event.setResult(Result.ALLOW);
		}
	}
	
	private static ItemStack insertInBackpacks(ServerPlayerEntity player, ItemStack stackToPickup) {
		final PlayerInventory playerInventory = player.inventory;
		
		for (int index = 0; index < playerInventory.getSizeInventory(); index++) {
			final ItemStack stack = playerInventory.getStackInSlot(index);
			final Item item = stack.getItem();
			
			if (item instanceof IBackpack) {
				final IBackpack backpack = (IBackpack) item;
				final IInventory inventory = backpack.getInventory(player, stack);
				final IItemHandler itemHandler = new InvWrapper(inventory);
				final ItemStack result = ItemHandlerHelper.insertItemStacked(itemHandler, stackToPickup, false);
				if (result.getCount() != stackToPickup.getCount()) {
					backpack.saveInventory(inventory);
				}
				stackToPickup = result;
				if (stackToPickup.isEmpty()) {
					break;
				}
			}
		}
		return stackToPickup;
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(EventPriority.HIGHEST, ItemPickupEventHandler::onEntityItemPickup);
	}
	
}
