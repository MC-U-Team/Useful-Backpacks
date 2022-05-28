package info.u_team.useful_backpacks.integration.curios.network;

import java.util.Optional;
import java.util.function.Supplier;

import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.integration.curios.util.BackpackCuriosUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent.Context;
import top.theillusivec4.curios.api.SlotResult;

public class OpenBackpackMessage {
	
	public static void encode(OpenBackpackMessage message, FriendlyByteBuf byteBuf) {
	}
	
	public static OpenBackpackMessage decode(FriendlyByteBuf byteBuf) {
		return new OpenBackpackMessage();
	}
	
	public static class Handler {
		
		public static void handle(OpenBackpackMessage message, Supplier<Context> contextSupplier) {
			final Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				final ServerPlayer player = context.getSender();
				if (!player.isAlive()) {
					return;
				}
				final Optional<ItemStack> curioBackpack = BackpackCuriosUtil.getBackpack(player).map(SlotResult::stack);
				if (curioBackpack.isPresent()) {
					final ItemStack stack = curioBackpack.get();
					if (stack.getItem() instanceof Backpack backpack) {
						backpack.open(player, stack, -2);
					}
				}
			});
			context.setPacketHandled(true);
		}
	}
	
}
