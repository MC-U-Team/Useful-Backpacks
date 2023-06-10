package info.u_team.useful_backpacks.api;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public interface Backpack {
	
	void open(ServerPlayer player, ItemStack backpackStack, int selectedSlot);
	
	SimpleContainer getInventory(ServerPlayer player, ItemStack backpackStack);
	
	default void saveInventory(SimpleContainer inventory, ItemStack backpackStack) {
	}
	
	default boolean canAutoPickup(ItemStack stack, ItemStack backpackStack) {
		return false;
	}
}
