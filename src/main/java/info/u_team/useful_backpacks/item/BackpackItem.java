package info.u_team.useful_backpacks.item;

import java.util.Arrays;
import java.util.List;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.config.ServerConfig;
import info.u_team.useful_backpacks.container.BackpackContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import info.u_team.useful_backpacks.inventory.BackpackInventory;
import info.u_team.useful_backpacks.type.Backpack;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class BackpackItem extends UItem implements AutoPickupBackpack, IDyeableItem {
	
	private final Backpack backpack;
	
	public BackpackItem(Backpack backpack) {
		super(UsefulBackpacksItemGroups.GROUP, new Properties().maxStackSize(1).rarity(backpack.getRarity()));
		this.backpack = backpack;
		addColoredItem(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
			open((ServerPlayerEntity) player, stack, hand == Hand.MAIN_HAND ? player.inventory.currentItem : -1);
		}
		return ActionResult.resultSuccess(stack);
	}
	
	@Override
	public void open(ServerPlayerEntity player, ItemStack backpackStack, int selectedSlot) {
		NetworkHooks.openGui(player, new SimpleNamedContainerProvider((id, playerInventory, unused) -> {
			return new BackpackContainer(id, playerInventory, getInventory(player, backpackStack), backpack, selectedSlot);
		}, backpackStack.getDisplayName()), buffer -> {
			buffer.writeEnumValue(backpack);
			buffer.writeVarInt(selectedSlot);
		});
	}
	
	@Override
	public IInventory getInventory(ServerPlayerEntity player, ItemStack backpackStack) {
		return new BackpackInventory(backpackStack, backpack.getInventorySize());
	}
	
	@Override
	public void saveInventory(IInventory inventory, ItemStack backpackStack) {
		if (inventory instanceof BackpackInventory) {
			((BackpackInventory) inventory).writeItemStack();
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		addTooltip(stack, world, tooltip, flag);
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
