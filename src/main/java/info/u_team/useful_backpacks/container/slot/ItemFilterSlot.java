package info.u_team.useful_backpacks.container.slot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ItemFilterSlot extends Slot {
	
	public ItemFilterSlot(Container inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
	}
	
	@Override
	public ItemStack onTake(Player player, ItemStack stack) {
		return stack;
	}
	
	@Override
	public ItemStack remove(int amound) {
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean mayPickup(Player player) {
		return false;
	}
}
