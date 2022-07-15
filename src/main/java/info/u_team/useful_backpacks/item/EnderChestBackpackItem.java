package info.u_team.useful_backpacks.item;

import java.util.List;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.init.UsefulBackpacksCreativeTabs;
import info.u_team.useful_backpacks.menu.EnderChestBackpackMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class EnderChestBackpackItem extends UItem implements AutoPickupBackpack {
	
	public EnderChestBackpackItem() {
		super(UsefulBackpacksCreativeTabs.TAB, new Properties().stacksTo(1).rarity(Rarity.EPIC));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		final ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide && player instanceof ServerPlayer) {
			open((ServerPlayer) player, stack, hand == InteractionHand.MAIN_HAND ? player.getInventory().selected : -1);
		}
		return InteractionResultHolder.success(stack);
	}
	
	@Override
	public void open(ServerPlayer player, ItemStack backpackStack, int selectedSlot) {
		NetworkHooks.openScreen(player, new SimpleMenuProvider((id, playerInventory, unused) -> {
			return EnderChestBackpackMenu.createEnderChestContainer(id, playerInventory, getInventory(player, backpackStack), selectedSlot);
		}, backpackStack.getHoverName()), buffer -> {
			buffer.writeVarInt(selectedSlot);
		});
	}
	
	@Override
	public Container getInventory(ServerPlayer player, ItemStack backpackStack) {
		return player.getEnderChestInventory();
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		addTooltip(stack, level, tooltip, flag);
	}
}
