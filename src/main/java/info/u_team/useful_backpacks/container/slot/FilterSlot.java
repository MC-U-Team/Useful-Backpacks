package info.u_team.useful_backpacks.container.slot;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.api.IFilter;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FilterSlot extends Slot {
	
	public static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "item/empty_filter_slot");
	
	private final IInventory backpackSlotInventory;
	
	public FilterSlot(IInventory backpackSlotInventory, IInventory inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
		this.backpackSlotInventory = backpackSlotInventory;
		setBackground(PlayerContainer.LOCATION_BLOCKS_TEXTURE, BACKGROUND);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IFilter && ((IFilter) stack.getItem()).isUsable(stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isEnabled() {
		return !backpackSlotInventory.isEmpty();
	}
	
}
