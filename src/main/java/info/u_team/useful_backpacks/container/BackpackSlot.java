package info.u_team.useful_backpacks.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;

public class BackpackSlot extends Slot {
	
	public BackpackSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
}
