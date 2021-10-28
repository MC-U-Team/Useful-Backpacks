package info.u_team.useful_backpacks.container.slot;

import info.u_team.useful_backpacks.config.CommonConfig;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BackpackSlot extends Slot {
	
	public BackpackSlot(Container inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		if (stack.getItem() instanceof BackpackItem) {
			return CommonConfig.getInstance().allowStackingBackpacks.get();
		}
		return true;
	}
	
}
