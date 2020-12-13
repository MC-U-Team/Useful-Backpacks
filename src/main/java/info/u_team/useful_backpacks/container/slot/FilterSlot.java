package info.u_team.useful_backpacks.container.slot;

import info.u_team.useful_backpacks.api.*;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.*;

public class FilterSlot extends Slot {
	
	private final IInventory backpackSlotInventory;
	
	public FilterSlot(IInventory backpackSlotInventory, IInventory inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
		this.backpackSlotInventory = backpackSlotInventory;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IFilter;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isEnabled() {
		return !backpackSlotInventory.isEmpty();
	}
	
}
