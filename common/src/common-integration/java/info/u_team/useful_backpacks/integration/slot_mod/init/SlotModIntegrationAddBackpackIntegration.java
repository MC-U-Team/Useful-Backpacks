package info.u_team.useful_backpacks.integration.slot_mod.init;

import java.util.Optional;

import info.u_team.useful_backpacks.event.ItemPickupCommonEventHandler;
import info.u_team.useful_backpacks.integration.slot_mod.util.BackpackSlotModUtil;
import net.minecraft.world.item.ItemStack;

public class SlotModIntegrationAddBackpackIntegration {
	
	public static void register() {
		ItemPickupCommonEventHandler.INTEGRATION_BACKPACKS.add((player) -> {
			final Optional<ItemStack> backpackStack = BackpackSlotModUtil.findBackpack(player);
			if (backpackStack.isPresent()) {
				return backpackStack.get();
			}
			return ItemStack.EMPTY;
		});
	}
	
}
