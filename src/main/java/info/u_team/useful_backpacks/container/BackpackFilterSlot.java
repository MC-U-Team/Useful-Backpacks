package info.u_team.useful_backpacks.container;

import info.u_team.useful_backpacks.api.IBackpack;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class BackpackFilterSlot extends Slot {
	
	public BackpackFilterSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IBackpack;
	}
	
}
