package info.u_team.useful_backpacks.menu.slot;

import com.mojang.datafixers.util.Pair;

import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.api.Backpack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BackpackFilterSlot extends Slot {
	
	public static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksReference.MODID, "item/empty_backpack_filter_slot");
	
	private static final Pair<ResourceLocation, ResourceLocation> ICON = Pair.of(InventoryMenu.BLOCK_ATLAS, BACKGROUND);
	
	public BackpackFilterSlot(Container inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
	}
	
	@Override
	public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
		return ICON;
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() instanceof Backpack;
	}
}
