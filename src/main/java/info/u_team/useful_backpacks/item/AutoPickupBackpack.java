package info.u_team.useful_backpacks.item;

import java.util.ArrayList;
import java.util.List;

import info.u_team.u_team_core.util.TooltipCreator;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.api.Filter;
import info.u_team.useful_backpacks.inventory.FilterInventory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public interface AutoPickupBackpack extends Backpack {
	
	@Override
	default boolean canAutoPickup(ItemStack stack, ItemStack backpackStack) {
		final var filterInventory = new FilterInventory(backpackStack);
		
		for (var index = 0; index < filterInventory.getContainerSize(); index++) {
			final var filterStack = filterInventory.getItem(index);
			final var filterItem = filterStack.getItem();
			if (filterItem instanceof Filter filter) {
				if (filter.matchItem(filterStack, stack)) {
					return true;
				}
			}
		}
		return false;
	}
	
	default void addTooltip(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		final List<ItemStack> filters = new ArrayList<>();
		
		final var filterInventory = new FilterInventory(stack);
		
		for (var index = 0; index < filterInventory.getContainerSize(); index++) {
			final var filterStack = filterInventory.getItem(index);
			if (filterStack.getItem() instanceof Filter) {
				filters.add(filterStack);
			}
		}
		
		if (!filters.isEmpty()) {
			tooltip.add(TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter", 0).withStyle(ChatFormatting.GREEN, ChatFormatting.ITALIC));
			tooltip.add(TextComponent.EMPTY);
			if (!flag.isAdvanced()) {
				tooltip.add(TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_not_advanced", 0).withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
			} else {
				tooltip.add(TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_applied", 0).withStyle(ChatFormatting.AQUA));
				
				filters.stream().filter(filterStack -> filterStack.getItem() instanceof ItemFilterItem).forEach(filterStack -> {
					final var component = TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_applied_item", 0, new TranslatableComponent(ItemStack.of(filterStack.getTag().getCompound("stack")).getDescriptionId()).withStyle(ChatFormatting.YELLOW));
					if (filterStack.getTag().getBoolean("strict")) {
						component.append(" ").append(TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_applied_item", 1));
					}
					component.withStyle(ChatFormatting.GRAY);
					tooltip.add(component);
				});
				
				filters.stream().filter(filterStack -> filterStack.getItem() instanceof TagFilterItem).forEach(filterStack -> {
					final var component = TooltipCreator.create(UsefulBackpacksMod.MODID, "backpack", "filter_applied_tag", 0, new TextComponent(filterStack.getTag().getString("id")).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY);
					tooltip.add(component);
				});
			}
		}
	}
}
