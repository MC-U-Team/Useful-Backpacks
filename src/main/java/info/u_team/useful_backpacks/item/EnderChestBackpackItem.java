package info.u_team.useful_backpacks.item;

import java.util.List;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.container.EnderChestBackpackContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EnderChestBackpackItem extends UItem implements AutoPickupBackpack {
	
	public EnderChestBackpackItem() {
		super(UsefulBackpacksItemGroups.GROUP, new Properties().maxStackSize(1).rarity(Rarity.EPIC));
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
			return EnderChestBackpackContainer.createEnderChestContainer(id, playerInventory, getInventory(player, backpackStack), selectedSlot);
		}, backpackStack.getDisplayName()), buffer -> {
			buffer.writeVarInt(selectedSlot);
		});
	}
	
	@Override
	public IInventory getInventory(ServerPlayerEntity player, ItemStack backpackStack) {
		return player.getInventoryEnderChest();
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		addTooltip(stack, world, tooltip, flag);
	}
}
