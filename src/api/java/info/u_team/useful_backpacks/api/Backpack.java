package info.u_team.useful_backpacks.api;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

public interface Backpack {
	
	void open(ServerPlayer player, ItemStack backpackStack, int selectedSlot);
	
	Container getInventory(ServerPlayer player, ItemStack backpackStack);
	
	default void saveInventory(Container inventory, ItemStack backpackStack) {
	}
	
	default boolean canAutoPickup(ItemStack stack, ItemStack backpackStack) {
		return false;
	}
}
