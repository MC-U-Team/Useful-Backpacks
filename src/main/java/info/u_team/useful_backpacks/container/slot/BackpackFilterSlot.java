package info.u_team.useful_backpacks.container.slot;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.api.IBackpack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BackpackFilterSlot extends Slot {
	
	public static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "item/empty_backpack_filter_slot");
	
	private final IInventory filterSlotInventory;
	
	public BackpackFilterSlot(IInventory filterSlotInventory, IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.filterSlotInventory = filterSlotInventory;
		setBackground(PlayerContainer.LOCATION_BLOCKS_TEXTURE, BACKGROUND);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IBackpack;
	}
	
	@Override
	public ItemStack onTake(PlayerEntity player, ItemStack stack) {
		filterSlotInventory.clear();
		return super.onTake(player, stack);
	}
	
}
