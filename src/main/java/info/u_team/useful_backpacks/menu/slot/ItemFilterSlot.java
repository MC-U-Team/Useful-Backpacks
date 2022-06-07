package info.u_team.useful_backpacks.menu.slot;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ItemFilterSlot extends Slot {
	
	public ItemFilterSlot(Container container, int index, int x, int y) {
		super(container, index, x, y);
	}
	
	@Override
	public void onTake(Player player, ItemStack stack) {
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
