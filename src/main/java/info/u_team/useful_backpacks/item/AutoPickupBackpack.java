package info.u_team.useful_backpacks.item;

import java.util.*;

import info.u_team.u_team_core.util.TooltipCreator;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.api.*;
import info.u_team.useful_backpacks.inventory.FilterInventory;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.*;

public interface AutoPickupBackpack extends IBackpack {
	
	@Override
	default boolean canAutoPickup(ItemStack stack, ItemStack backpackStack) {
		final FilterInventory filterInventory = new FilterInventory(backpackStack);
		
		for (int index = 0; index < filterInventory.getSizeInventory(); index++) {
			final ItemStack filterStack = filterInventory.getStackInSlot(index);
			final Item filterItem = filterStack.getItem();
			if (filterItem instanceof IFilter) {
				final IFilter filter = (IFilter) filterItem;
				
				if (filter.matchItem(filterStack, stack)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@OnlyIn(Dist.CLIENT)
	default void addTooltip(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		final List<ItemStack> filters = new ArrayList<>();
		
		final FilterInventory filterInventory = new FilterInventory(stack);
		
		for (int index = 0; index < filterInventory.getSizeInventory(); index++) {
			final ItemStack filterStack = filterInventory.getStackInSlot(index);
			if (filterStack.getItem() instanceof IFilter) {
				filters.add(filterStack);
			}
		}
		
		if (!filters.isEmpty()) {
			tooltip.add(TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter", 0).mergeStyle(TextFormatting.GREEN, TextFormatting.ITALIC));
			tooltip.add(ITextComponent.getTextComponentOrEmpty(null));
			if (!flag.isAdvanced()) {
				tooltip.add(TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_not_advanced", 0).mergeStyle(TextFormatting.RED, TextFormatting.ITALIC));
			} else {
				tooltip.add(TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_applied", 0).mergeStyle(TextFormatting.AQUA));
				
				filters.stream().filter(filterStack -> filterStack.getItem() instanceof ItemFilterItem).forEach(filterStack -> {
					final IFormattableTextComponent component = TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_applied_item", 0, new TranslationTextComponent(ItemStack.read(filterStack.getTag().getCompound("stack")).getTranslationKey()).mergeStyle(TextFormatting.YELLOW));
					if (filterStack.getTag().getBoolean("strict")) {
						component.appendString(" ").appendSibling(TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_applied_item", 1));
					}
					component.mergeStyle(TextFormatting.GRAY);
					tooltip.add(new StringTextComponent("").appendSibling(component));
				});
				
				filters.stream().filter(filterStack -> filterStack.getItem() instanceof TagFilterItem).forEach(filterStack -> {
					final IFormattableTextComponent component = TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_applied_tag", 0, new StringTextComponent(filterStack.getTag().getString("id")).mergeStyle(TextFormatting.YELLOW)).mergeStyle(TextFormatting.GRAY);
					tooltip.add(new StringTextComponent("").appendSibling(component));
				});
			}
		}
	}
}
