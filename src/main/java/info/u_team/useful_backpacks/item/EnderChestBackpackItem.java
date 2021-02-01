package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.container.EnderChestBackpackContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EnderChestBackpackItem extends UItem implements IBackpack {
	
	public EnderChestBackpackItem() {
		super(UsefulBackpacksItemGroups.GROUP, new Properties().maxStackSize(1).rarity(Rarity.EPIC));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
			open((ServerPlayerEntity) player, stack, hand == Hand.MAIN_HAND ? player.inventory.currentItem : -1);
		}
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}
	
	@Override
	public void open(ServerPlayerEntity player, ItemStack backpackStack, int selectedSlot) {
		NetworkHooks.openGui(player, new SimpleNamedContainerProvider((id, playerInventory, unused) -> {
			return EnderChestBackpackContainer.createEnderChestContainer(id, playerInventory, getInventory(player, backpackStack), selectedSlot);
		}, backpackStack.getDisplayName()), buffer -> {
			buffer.writeVarInt(selectedSlot);
		});
	}
	
	@Override
	public IInventory getInventory(ServerPlayerEntity player, ItemStack backpackStack) {
		return player.getInventoryEnderChest();
	}
}
