package info.u_team.useful_backpacks.container.slot;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.api.Filter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

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
		return stack.getItem() instanceof Filter && ((Filter) stack.getItem()).isUsable(stack);
	}
	
	@Override
	public boolean isActive() {
		return !backpackSlotInventory.isEmpty();
	}
	
}
