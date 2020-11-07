package info.u_team.useful_backpacks.container;

import info.u_team.useful_backpacks.config.CommonConfig;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class SlotBackpack extends Slot {
	
	public SlotBackpack(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() instanceof ItemBackPack) {
			return CommonConfig.allowStackingBackpacks;
		}
		return super.isItemValid(stack);
	}
	
}