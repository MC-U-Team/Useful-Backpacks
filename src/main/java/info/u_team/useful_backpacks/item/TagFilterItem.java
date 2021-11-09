package info.u_team.useful_backpacks.item;

import java.util.List;

import info.u_team.u_team_core.util.TooltipCreator;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.TagFilterContainer;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

public class TagFilterItem extends FilterItem {
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		final ItemStack stack = player.getItemInHand(hand);
		if (!world.isClientSide && player instanceof ServerPlayer) {
			if (player.isShiftKeyDown()) {
				stack.removeTagKey("id");
			} else {
				final int selectedSlot = hand == InteractionHand.MAIN_HAND ? player.getInventory().selected : -1;
				final String tag;
				if (stack.hasTag()) {
					tag = stack.getTag().getString("id");
				} else {
					tag = "";
				}
				NetworkHooks.openGui((ServerPlayer) player, new SimpleMenuProvider((id, playerInventory, unused) -> {
					return new TagFilterContainer(id, playerInventory, stack, selectedSlot, tag);
				}, stack.getHoverName()), buffer -> {
					buffer.writeVarInt(selectedSlot);
					buffer.writeUtf(tag);
				});
			}
		}
		return InteractionResultHolder.success(stack);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, Player player) {
		return !(player.containerMenu instanceof TagFilterContainer);
	}
	
	@Override
	protected boolean matchItem(ItemStack filterStack, ItemStack matchStack, CompoundTag compound) {
		final ResourceLocation id = ResourceLocation.tryParse(compound.getString("id"));
		
		if (id != null) {
			return matchStack.getItem().getTags().contains(id);
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isUsable(ItemStack filterStack, CompoundTag compound) {
		return filterStack.getItem() instanceof TagFilterItem && compound.contains("id");
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
		if (!isUsable(stack)) {
			tooltip.add(TooltipCreator.create(this, "not_configured", 0).withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
			tooltip.add(TooltipCreator.create(this, "not_configured", 1, TooltipCreator.create(UsefulBackpacksMod.MODID, "click", "right_click", 0).withStyle(ChatFormatting.ITALIC, ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
		} else {
			tooltip.add(TooltipCreator.create(this, "configured", 0).withStyle(ChatFormatting.GREEN, ChatFormatting.ITALIC));
			tooltip.add(TooltipCreator.create(this, "configured", 1, new TextComponent(stack.getTag().getString("id")).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
			tooltip.add(TooltipCreator.create(this, "configured", 2, TooltipCreator.create(UsefulBackpacksMod.MODID, "click", "shift_right_click", 0).withStyle(ChatFormatting.ITALIC, ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
		}
	}
}
