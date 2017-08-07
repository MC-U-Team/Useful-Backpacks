package info.u_team.usefulbackpacks.inventory;

import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class SlotBackPack extends Slot {
	
	public SlotBackPack(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack != null && stack.getItem() != null && stack.stackSize > 0) {
			return true;
		}
		return false;
	}
	
}
