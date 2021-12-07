package info.u_team.useful_backpacks.item;

import java.util.List;

import info.u_team.u_team_core.util.TooltipCreator;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.ItemFilterContainer;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemFilterItem extends FilterItem {
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		final var stack = player.getItemInHand(hand);
		if (!level.isClientSide && player instanceof ServerPlayer) {
			if (player.isShiftKeyDown()) {
				stack.removeTagKey("strict");
				stack.removeTagKey("stack");
			} else {
				final var selectedSlot = hand == InteractionHand.MAIN_HAND ? player.getInventory().selected : -1;
				final boolean isStrict;
				if (stack.hasTag()) {
					isStrict = stack.getTag().getBoolean("strict");
				} else {
					isStrict = false;
				}
				NetworkHooks.openGui((ServerPlayer) player, new SimpleMenuProvider((id, playerInventory, unused) -> {
					return new ItemFilterContainer(id, playerInventory, stack, selectedSlot, isStrict);
				}, stack.getHoverName()), buffer -> {
					buffer.writeVarInt(selectedSlot);
					buffer.writeBoolean(isStrict);
				});
			}
		}
		return InteractionResultHolder.success(stack);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, Player player) {
		return !(player.containerMenu instanceof ItemFilterContainer);
	}
	
	@Override
	protected boolean matchItem(ItemStack filterStack, ItemStack matchStack, CompoundTag compound) {
		final var strict = compound.getBoolean("strict");
		final var stack = ItemStack.of(compound.getCompound("stack"));
		
		if (strict) {
			return ItemHandlerHelper.canItemStacksStack(stack, matchStack);
		} else {
			return stack.sameItem(matchStack);
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
			tooltip.add(TooltipCreator.create(this, "not_configured", 1, TooltipCreator.create(UsefulBackpacksMod.MODID, "click", "right_click", 0).withStyle(ChatFormatting.ITALIC, ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
		} else {
			tooltip.add(TooltipCreator.create(this, "configured", 0).withStyle(ChatFormatting.GREEN, ChatFormatting.ITALIC));
			tooltip.add(TooltipCreator.create(this, "configured", 1, new TranslatableComponent(ItemStack.of(stack.getTag().getCompound("stack")).getDescriptionId()).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
			tooltip.add(TooltipCreator.create(this, "configured", 2, new TextComponent(Boolean.toString(stack.getTag().getBoolean("strict"))).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
			tooltip.add(TooltipCreator.create(this, "configured", 3, TooltipCreator.create(UsefulBackpacksMod.MODID, "click", "shift_right_click", 0).withStyle(ChatFormatting.ITALIC, ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
		}
	}
}
