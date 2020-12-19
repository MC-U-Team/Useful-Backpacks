package info.u_team.useful_backpacks.container.slot;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.api.IBackpack;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BackpackFilterSlot extends Slot {
	
	public static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "item/empty_backpack_filter_slot");
	
	public BackpackFilterSlot(IInventory inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
		setBackground(PlayerContainer.LOCATION_BLOCKS_TEXTURE, BACKGROUND);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IBackpack;
	}
}
