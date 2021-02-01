package info.u_team.useful_backpacks.api;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IBackpack {
	
	void open(ServerPlayerEntity player, ItemStack backpackStack, int selectedSlot);
	
	IInventory getInventory(ServerPlayerEntity player, ItemStack backpackStack);
	
	default void saveInventory(IInventory inventory, ItemStack backpackStack) {
	}
	
	default boolean canAutoPickup(ItemStack stack, ItemStack backpackStack) {
		return false;
	}
}
