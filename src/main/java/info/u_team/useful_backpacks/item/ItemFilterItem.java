package info.u_team.useful_backpacks.item;

import info.u_team.useful_backpacks.container.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemFilterItem extends FilterItem {
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
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
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
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
}
