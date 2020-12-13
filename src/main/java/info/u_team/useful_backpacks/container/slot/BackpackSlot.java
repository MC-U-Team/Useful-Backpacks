package info.u_team.useful_backpacks.container.slot;

import info.u_team.useful_backpacks.config.CommonConfig;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class BackpackSlot extends Slot {
	
	public BackpackSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() instanceof BackpackItem) {
			return CommonConfig.getInstance().allowStackingBackpacks.get();
		}
		return true;
	}
	
}
