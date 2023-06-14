package info.u_team.useful_backpacks.integration.slot_mod.message;

import java.util.Optional;

import info.u_team.u_team_core.api.network.NetworkContext;
import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.integration.slot_mod.util.BackpackSlotModUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class OpenBackpackMessage {
	
	public static void encode(OpenBackpackMessage message, FriendlyByteBuf buffer) {
	}
	
	public static OpenBackpackMessage decode(FriendlyByteBuf buffer) {
		return new OpenBackpackMessage();
	}
	
	public static class Handler {
		
		public static void handle(OpenBackpackMessage message, NetworkContext context) {
			if (!(context.getPlayer() instanceof ServerPlayer player)) {
				return;
			}
			context.executeOnMainThread(() -> {
				if (!player.isAlive()) {
					return;
				}
				if (player.hasContainerOpen()) {
					return;
				}
				final Optional<ItemStack> curioBackpack = BackpackSlotModUtil.findBackpack(player);
				if (curioBackpack.isPresent()) {
					final ItemStack stack = curioBackpack.get();
					if (stack.getItem() instanceof final Backpack backpack) {
						backpack.open(player, stack, -2);
					}
				}
			});
		}
	}
	
}
