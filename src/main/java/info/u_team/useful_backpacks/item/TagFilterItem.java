package info.u_team.useful_backpacks.item;

import java.util.List;

import info.u_team.useful_backpacks.container.TagFilterContainer;
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

public class TagFilterItem extends FilterItem {
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
			final int selectedSlot = hand == Hand.MAIN_HAND ? player.inventory.currentItem : -1;
			final String tag;
			if (stack.hasTag()) {
				tag = stack.getTag().getString("id");
			} else {
				tag = "";
			}
			NetworkHooks.openGui((ServerPlayerEntity) player, new SimpleNamedContainerProvider((id, playerInventory, unused) -> {
				return new TagFilterContainer(id, playerInventory, stack, selectedSlot, tag);
			}, stack.getDisplayName()), buffer -> {
				buffer.writeVarInt(selectedSlot);
				buffer.writeString(tag);
			});
			
		}
		return ActionResult.resultSuccess(stack);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
		return !(player.openContainer instanceof TagFilterContainer);
	}
	
	@Override
	protected boolean matchItem(ItemStack filterStack, ItemStack matchStack, CompoundNBT compound) {
		final ResourceLocation id = ResourceLocation.tryCreate(compound.getString("id"));
		
		if (id != null) {
			return matchStack.getItem().getTags().contains(id);
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isUsable(ItemStack filterStack, CompoundNBT compound) {
		return filterStack.getItem() instanceof TagFilterItem && compound.contains("id");
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (!isUsable(stack)) {
			tooltip.add(new TranslationTextComponent(getTranslationKey() + ".tooltip.0").mergeStyle(TextFormatting.RED, TextFormatting.ITALIC));
			tooltip.add(new TranslationTextComponent(getTranslationKey() + ".tooltip.1", new TranslationTextComponent("usefulbackpacks.tooltip.right_click").mergeStyle(TextFormatting.ITALIC, TextFormatting.GOLD)).mergeStyle(TextFormatting.GRAY));
		}
	}
	
}
