package info.u_team.useful_backpacks.menu.slot;

import com.mojang.datafixers.util.Pair;

import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.api.Filter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FilterSlot extends Slot {
	
	public static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksReference.MODID, "item/empty_filter_slot");
	
	private static final Pair<ResourceLocation, ResourceLocation> ICON = Pair.of(InventoryMenu.BLOCK_ATLAS, BACKGROUND);
	
	private final Container backpackSlotInventory;
	
	public FilterSlot(Container backpackSlotInventory, Container container, int index, int x, int y) {
		super(container, index, x, y);
		this.backpackSlotInventory = backpackSlotInventory;
	}
	
	@Override
	public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
		return ICON;
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() instanceof final Filter filter && filter.isUsable(stack) && isActive();
	}
	
	@Override
	public boolean isActive() {
		return !backpackSlotInventory.isEmpty();
	}
	
}
