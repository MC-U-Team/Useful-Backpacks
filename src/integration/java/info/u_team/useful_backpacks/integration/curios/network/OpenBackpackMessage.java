package info.u_team.useful_backpacks.integration.curios.network;

import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.integration.curios.util.BackpackCuriosUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent.Context;

public class OpenBackpackMessage {
	
	public static void encode(OpenBackpackMessage message, FriendlyByteBuf byteBuf) {
	}
	
	public static OpenBackpackMessage decode(FriendlyByteBuf byteBuf) {
		return new OpenBackpackMessage();
	}
	
	public static class Handler {
		
		public static void handle(OpenBackpackMessage message, Supplier<Context> contextSupplier) {
			final var context = contextSupplier.get();
			context.enqueueWork(() -> {
				final var player = context.getSender();
				if (!player.isAlive()) {
					// TODO more conditions?
				}
				final var curioBackpack = BackpackCuriosUtil.getBackpack(player).map(ImmutableTriple::getRight);
				if (curioBackpack.isPresent()) {
					final var stack = curioBackpack.get();
					if (stack.getItem()instanceof Backpack backpack) {
						backpack.open(player, stack, -2);
					}
				}
			});
			context.setPacketHandled(true);
		}
	}
	
}
