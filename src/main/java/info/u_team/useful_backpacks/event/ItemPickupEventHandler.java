package info.u_team.useful_backpacks.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.config.CommonConfig;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ItemPickupEventHandler {
	
	public static final List<Function<ServerPlayerEntity, ItemStack>> INTEGRATION_BACKPACKS = new ArrayList<>();
	
	private static void onEntityItemPickup(EntityItemPickupEvent event) {
		final PlayerEntity player = event.getPlayer();
		
		if (!(player instanceof ServerPlayerEntity)) {
			return;
		}
		
		final ItemStack stackToPickup = event.getItem().getItem();
		if (stackToPickup.getItem() instanceof BackpackItem && !CommonConfig.getInstance().allowStackingBackpacks.get()) { // TODO Move somewhere else
			return;
		}
		final ItemStack resultStack = insertInBackpacks((ServerPlayerEntity) player, stackToPickup);
		stackToPickup.setCount(resultStack.getCount());
		
		if (resultStack.isEmpty()) {
			event.setResult(Result.ALLOW);
		}
		
	}
	
	private static ItemStack insertInBackpacks(ServerPlayerEntity player, ItemStack stackToPickup) {
		final PlayerInventory playerInventory = player.inventory;
		
		for (final Function<ServerPlayerEntity, ItemStack> function : INTEGRATION_BACKPACKS) {
			final ItemStack stack = function.apply(player);
			if (!stack.isEmpty()) {
				stackToPickup = insertInBackpack(player, stack, stackToPickup);
				if (stackToPickup.isEmpty()) {
					return stackToPickup;
				}
			}
		}
		
		for (int index = 0; index < playerInventory.getSizeInventory(); index++) {
			final ItemStack stack = playerInventory.getStackInSlot(index);
			
			stackToPickup = insertInBackpack(player, stack, stackToPickup);
			if (stackToPickup.isEmpty()) {
				return stackToPickup;
			}
		}
		return stackToPickup;
	}
	
	private static ItemStack insertInBackpack(ServerPlayerEntity player, ItemStack stack, ItemStack stackToPickup) {
		final Item item = stack.getItem();
		
		if (item instanceof IBackpack) {
			final IBackpack backpack = (IBackpack) item;
			
			if (backpack.canAutoPickup(stackToPickup, stack)) {
				final IInventory inventory = backpack.getInventory(player, stack);
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
