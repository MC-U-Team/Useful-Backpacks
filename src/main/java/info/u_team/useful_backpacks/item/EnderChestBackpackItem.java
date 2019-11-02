package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.container.EnderChestBackpackContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class EnderChestBackpackItem extends UItem {
	
	public EnderChestBackpackItem(String name) {
		super(name, UsefulBackpacksItemGroups.GROUP, new Properties().maxStackSize(1).rarity(Rarity.EPIC));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
			player.openContainer(new SimpleNamedContainerProvider((id, playerInventory, openPlayer) -> {
				return EnderChestBackpackContainer.createEnderChestContainer(id, playerInventory, openPlayer.getInventoryEnderChest());
			}, stack.getDisplayName()));
		}
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}
}
