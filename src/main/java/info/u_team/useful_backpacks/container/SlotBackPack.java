package info.u_team.useful_backpacks.container;

import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class SlotBackPack extends Slot {
	
	public SlotBackPack(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (!stack.isEmpty()) {
			return true;
		}
		return false;
	}
	
}
