package info.u_team.useful_backpacks.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.config.CommonConfig;
import info.u_team.useful_backpacks.inventory.BackpackInventory;
import info.u_team.useful_backpacks.item.BackpackItem;
import info.u_team.useful_backpacks.menu.BackpackMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemPickupCommonEventHandler {
	
	public static final List<Function<ServerPlayer, ItemStack>> INTEGRATION_BACKPACKS = new ArrayList<>();
	
	public static ItemStack insertInBackpacks(ServerPlayer player, ItemStack stackToPickup) {
		if (stackToPickup.getItem() instanceof Backpack && !CommonConfig.getInstance().allowStackingBackpacks().get()) {
			return stackToPickup;
		}
		
		if (player.containerMenu instanceof final BackpackMenu menu) {
			if (menu.getBackpackInventory() instanceof final BackpackInventory inventory) {
				if (inventory.getStack().getItem() instanceof final BackpackItem backpack) {
					if (backpack.canAutoPickup(stackToPickup, inventory.getStack())) {
						final ItemStack result = inventory.addItem(stackToPickup);
						if (result.getCount() != stackToPickup.getCount()) {
							inventory.writeItemStack();
						}
						stackToPickup = result;
						if (stackToPickup.isEmpty()) {
							return stackToPickup;
						}
					}
				}
				
				stackToPickup = insertInBackpack(player, inventory.getStack(), stackToPickup);
				if (stackToPickup.isEmpty()) {
					return stackToPickup;
				}
			}
		}
		
		for (final Function<ServerPlayer, ItemStack> function : INTEGRATION_BACKPACKS) {
			final ItemStack stack = function.apply(player);
			if (!stack.isEmpty()) {
				stackToPickup = insertInBackpack(player, stack, stackToPickup);
				if (stackToPickup.isEmpty()) {
					return stackToPickup;
				}
			}
		}
		
		final Inventory playerInventory = player.getInventory();
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
		
		if (item instanceof final Backpack backpack) {
			if (backpack.canAutoPickup(stackToPickup, stack)) {
				final SimpleContainer inventory = backpack.getInventory(player, stack);
				final ItemStack result = inventory.addItem(stackToPickup);
				if (result.getCount() != stackToPickup.getCount()) {
					backpack.saveInventory(inventory, stack);
				}
				stackToPickup = result;
			}
		}
		return stackToPickup;
	}
	
}
