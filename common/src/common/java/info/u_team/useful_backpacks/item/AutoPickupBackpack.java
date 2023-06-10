package info.u_team.useful_backpacks.item;

import java.util.ArrayList;
import java.util.List;

import info.u_team.u_team_core.util.TooltipCreator;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.api.Filter;
import info.u_team.useful_backpacks.inventory.FilterInventory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public interface AutoPickupBackpack extends Backpack {
	
	@Override
	default boolean canAutoPickup(ItemStack stack, ItemStack backpackStack) {
		final FilterInventory filterInventory = new FilterInventory(backpackStack);
		
		for (int index = 0; index < filterInventory.getContainerSize(); index++) {
			final ItemStack filterStack = filterInventory.getItem(index);
			final Item filterItem = filterStack.getItem();
			if (filterItem instanceof final Filter filter) {
				if (filter.matchItem(filterStack, stack)) {
					return true;
				}
			}
		}
		return false;
	}
	
	default void addTooltip(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		final List<ItemStack> filters = new ArrayList<>();
		
		final FilterInventory filterInventory = new FilterInventory(stack);
		
		for (int index = 0; index < filterInventory.getContainerSize(); index++) {
			final ItemStack filterStack = filterInventory.getItem(index);
			if (filterStack.getItem() instanceof Filter) {
				filters.add(filterStack);
			}
		}
		
		if (!filters.isEmpty()) {
			tooltip.add(TooltipCreator.create(UsefulBackpacksReference.MODID, "backpack", "filter", 0).withStyle(ChatFormatting.GREEN, ChatFormatting.ITALIC));
			tooltip.add(CommonComponents.EMPTY);
			if (!flag.isAdvanced()) {
				tooltip.add(TooltipCreator.create(UsefulBackpacksReference.MODID, "backpack", "filter_not_advanced", 0).withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
			} else {
				tooltip.add(TooltipCreator.create(UsefulBackpacksReference.MODID, "backpack", "filter_applied", 0).withStyle(ChatFormatting.AQUA));
				
				filters.stream().filter(filterStack -> filterStack.getItem() instanceof ItemFilterItem).forEach(filterStack -> {
					final MutableComponent component = TooltipCreator.create(UsefulBackpacksReference.MODID, "backpack", "filter_applied_item", 0, Component.translatable(ItemStack.of(filterStack.getTag().getCompound("stack")).getDescriptionId()).withStyle(ChatFormatting.YELLOW));
					if (filterStack.getTag().getBoolean("strict")) {
						component.append(" ").append(TooltipCreator.create(UsefulBackpacksReference.MODID, "backpack", "filter_applied_item", 1));
					}
					component.withStyle(ChatFormatting.GRAY);
					tooltip.add(component);
				});
				
				filters.stream().filter(filterStack -> filterStack.getItem() instanceof TagFilterItem).forEach(filterStack -> {
					final MutableComponent component = TooltipCreator.create(UsefulBackpacksReference.MODID, "backpack", "filter_applied_tag", 0, Component.literal(filterStack.getTag().getString("id")).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY);
					tooltip.add(component);
				});
			}
		}
	}
}
