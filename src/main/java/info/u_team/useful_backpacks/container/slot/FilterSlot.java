package info.u_team.useful_backpacks.container.slot;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.api.IFilter;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FilterSlot extends Slot {
	
	public static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "item/empty_filter_slot");
	
	private final Container backpackSlotInventory;
	
	public FilterSlot(Container backpackSlotInventory, Container inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
		this.backpackSlotInventory = backpackSlotInventory;
		setBackground(InventoryMenu.BLOCK_ATLAS, BACKGROUND);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() instanceof IFilter && ((IFilter) stack.getItem()).isUsable(stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isEnabled() {
		return !backpackSlotInventory.isEmpty();
	}
	
}
