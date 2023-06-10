package info.u_team.useful_backpacks.item;

import java.util.List;

import info.u_team.u_team_core.util.MenuUtil;
import info.u_team.u_team_core.util.TooltipCreator;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.menu.ItemFilterMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemFilterItem extends FilterItem {
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		final ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
			if (player.isShiftKeyDown()) {
				stack.removeTagKey("strict");
				stack.removeTagKey("stack");
			} else {
				final int selectedSlot = hand == InteractionHand.MAIN_HAND ? player.getInventory().selected : -1;
				final boolean isStrict;
				if (stack.hasTag()) {
					isStrict = stack.getTag().getBoolean("strict");
				} else {
					isStrict = false;
				}
				MenuUtil.openMenu(serverPlayer, new SimpleMenuProvider((id, playerInventory, unused) -> {
					return new ItemFilterMenu(id, playerInventory, stack, selectedSlot, isStrict);
				}, stack.getHoverName()), buffer -> {
					buffer.writeVarInt(selectedSlot);
					buffer.writeBoolean(isStrict);
				}, false);
			}
		}
		return InteractionResultHolder.success(stack);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, Player player) {
		return !(player.containerMenu instanceof ItemFilterMenu);
	}
	
	@Override
	protected boolean matchItem(ItemStack filterStack, ItemStack matchStack, CompoundTag compound) {
		final boolean strict = compound.getBoolean("strict");
		final ItemStack stack = ItemStack.of(compound.getCompound("stack"));
		
		if (strict) {
			return ItemHandlerHelper.canItemStacksStack(stack, matchStack);
		} else {
			return ItemStack.isSameItem(stack, matchStack);
		}
	}
	
	@Override
	protected boolean isUsable(ItemStack filterStack, CompoundTag compound) {
		return filterStack.getItem() instanceof ItemFilterItem && compound.contains("stack");
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		if (!isUsable(stack)) {
			tooltip.add(TooltipCreator.create(this, "not_configured", 0).withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
			tooltip.add(TooltipCreator.create(this, "not_configured", 1, TooltipCreator.create(UsefulBackpacksReference.MODID, "click", "right_click", 0).withStyle(ChatFormatting.ITALIC, ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
		} else {
			tooltip.add(TooltipCreator.create(this, "configured", 0).withStyle(ChatFormatting.GREEN, ChatFormatting.ITALIC));
			tooltip.add(TooltipCreator.create(this, "configured", 1, Component.translatable(ItemStack.of(stack.getTag().getCompound("stack")).getDescriptionId()).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
			tooltip.add(TooltipCreator.create(this, "configured", 2, Component.literal(Boolean.toString(stack.getTag().getBoolean("strict"))).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
			tooltip.add(TooltipCreator.create(this, "configured", 3, TooltipCreator.create(UsefulBackpacksReference.MODID, "click", "shift_right_click", 0).withStyle(ChatFormatting.ITALIC, ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
		}
	}
}
