package info.u_team.useful_backpacks.item;

import java.util.List;

import info.u_team.u_team_core.util.TooltipCreator;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.ItemFilterContainer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemFilterItem extends FilterItem {
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
			if (player.isSneaking()) {
				stack.removeChildTag("strict");
				stack.removeChildTag("stack");
			} else {
				final int selectedSlot = hand == Hand.MAIN_HAND ? player.inventory.currentItem : -1;
				final boolean isStrict;
				if (stack.hasTag()) {
					isStrict = stack.getTag().getBoolean("strict");
				} else {
					isStrict = false;
				}
				NetworkHooks.openGui((ServerPlayerEntity) player, new SimpleNamedContainerProvider((id, playerInventory, unused) -> {
					return new ItemFilterContainer(id, playerInventory, stack, selectedSlot, isStrict);
				}, stack.getDisplayName()), buffer -> {
					buffer.writeVarInt(selectedSlot);
					buffer.writeBoolean(isStrict);
				});
			}
		}
		return ActionResult.resultSuccess(stack);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
		return !(player.openContainer instanceof ItemFilterContainer);
	}
	
	@Override
	protected boolean matchItem(ItemStack filterStack, ItemStack matchStack, CompoundNBT compound) {
		final boolean strict = compound.getBoolean("strict");
		final ItemStack stack = ItemStack.read(compound.getCompound("stack"));
		
		if (strict) {
			return ItemHandlerHelper.canItemStacksStack(stack, matchStack);
		} else {
			return stack.isItemEqual(matchStack);
		}
	}
	
	@Override
	protected boolean isUsable(ItemStack filterStack, CompoundNBT compound) {
		return filterStack.getItem() instanceof ItemFilterItem && compound.contains("stack");
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (!isUsable(stack)) {
			tooltip.add(TooltipCreator.create(this, "not_configured", 0).mergeStyle(TextFormatting.RED, TextFormatting.ITALIC));
			tooltip.add(TooltipCreator.create(this, "not_configured", 1, TooltipCreator.create(UsefulBackpacksMod.MODID, "click", "right_click", 0).mergeStyle(TextFormatting.ITALIC, TextFormatting.GOLD)).mergeStyle(TextFormatting.GRAY));
		} else {
			tooltip.add(TooltipCreator.create(this, "configured", 0).mergeStyle(TextFormatting.GREEN, TextFormatting.ITALIC));
			tooltip.add(TooltipCreator.create(this, "configured", 1, new TranslationTextComponent(ItemStack.read(stack.getTag().getCompound("stack")).getTranslationKey()).mergeStyle(TextFormatting.YELLOW)).mergeStyle(TextFormatting.GRAY));
			tooltip.add(TooltipCreator.create(this, "configured", 2, new StringTextComponent(Boolean.toString(stack.getTag().getBoolean("strict"))).mergeStyle(TextFormatting.YELLOW)).mergeStyle(TextFormatting.GRAY));
			tooltip.add(TooltipCreator.create(this, "configured", 3, TooltipCreator.create(UsefulBackpacksMod.MODID, "click", "shift_right_click", 0).mergeStyle(TextFormatting.ITALIC, TextFormatting.GOLD)).mergeStyle(TextFormatting.GRAY));
		}
	}
}
