package info.u_team.useful_backpacks.menu.slot;

import info.u_team.useful_backpacks.config.CommonConfig;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BackpackSlot extends Slot {
	
	public BackpackSlot(Container container, int index, int x, int y) {
		super(container, index, x, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		if (stack.getItem() instanceof BackpackItem) {
			return CommonConfig.getInstance().allowStackingBackpacks().get();
		}
		return true;
	}
	
}
