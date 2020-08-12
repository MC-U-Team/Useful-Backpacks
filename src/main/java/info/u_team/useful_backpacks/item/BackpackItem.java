package info.u_team.useful_backpacks.item;

import java.util.Arrays;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.config.ServerConfig;
import info.u_team.useful_backpacks.container.BackpackContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import info.u_team.useful_backpacks.inventory.BackpackInventory;
import info.u_team.useful_backpacks.type.Backpack;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BackpackItem extends UItem implements IBackpack, IDyeableItem {
	
	private final Backpack backpack;
	
	public BackpackItem(Backpack backpack) {
		super(UsefulBackpacksItemGroups.GROUP, new Properties().maxStackSize(1).rarity(backpack.getRarity()));
		this.backpack = backpack;
		addColoredItem(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		final int selectedSlot = hand == Hand.MAIN_HAND ? player.inventory.currentItem : -1;
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
			open((ServerPlayerEntity) player, stack, selectedSlot);
		}
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}
	
	@Override
	public void open(ServerPlayerEntity player, ItemStack stack, int selectedSlot) {
		NetworkHooks.openGui(player, new SimpleNamedContainerProvider((id, playerInventory, openPlayer) -> {
			return new BackpackContainer(id, playerInventory, new BackpackInventory(stack, backpack.getInventorySize()), backpack, selectedSlot);
		}, stack.getDisplayName()), buffer -> {
			buffer.writeEnumValue(backpack);
			buffer.writeVarInt(selectedSlot);
		});
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.areItemsEqual(oldStack, newStack);
	}
	
	// Getter
	
	public Backpack getBackpack() {
		return backpack;
	}
	
	// Item group
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (!isInGroup(group)) {
			return;
		}
		items.add(new ItemStack(this));
		for (final DyeColor color : DyeColor.values()) {
			items.add(IDyeableItem.colorStack(new ItemStack(this, 1), Arrays.asList(color)));
		}
	}
	
	// Default backpack color if not present
	
	@Override
	public int getDefaultColor() {
		return 0x816040;
	}
	
	// Fix bug #22 (too large packet size with certain mod items) and kind of reverted (config option) with #24
	
	@Override
	public CompoundNBT getShareTag(ItemStack stack) {
		if (ServerConfig.getInstance().shareAllNBTData.get()) {
			return super.getShareTag(stack);
		}
		if (!stack.hasTag()) {
			return null;
		}
		final CompoundNBT compound = stack.getTag().copy();
		compound.remove("Items");
		if (compound.isEmpty()) {
			return null;
		}
		return compound;
	}
	
	// Fix bug #30 (dupe bug when lagging server)
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
		return !(player.openContainer instanceof BackpackContainer);
	}
}
