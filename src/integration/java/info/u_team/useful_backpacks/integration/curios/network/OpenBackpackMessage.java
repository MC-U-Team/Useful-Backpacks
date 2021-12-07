package info.u_team.useful_backpacks.integration.curios.network;

import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.integration.curios.util.BackpackCuriosUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent.Context;

public class OpenBackpackMessage {
	
	public static void encode(OpenBackpackMessage message, FriendlyByteBuf sendBuffer) {
	}
	
	public static OpenBackpackMessage decode(FriendlyByteBuf sendBuffer) {
		return new OpenBackpackMessage();
	}
	
	public static class Handler {
		
		public static void handle(OpenBackpackMessage message, Supplier<Context> contextSupplier) {
			final var context = contextSupplier.get();
			context.enqueueWork(() -> {
				final var player = context.getSender();
				final var curioBackpack = BackpackCuriosUtil.getBackpack(player).map(ImmutableTriple::getRight).filter(stack -> stack.getItem() instanceof Backpack);
				if (curioBackpack.isPresent()) {
					final var stack = curioBackpack.get();
					final var backpack = (Backpack) stack.getItem();
					backpack.open(player, stack, -2);
				}
			});
			context.setPacketHandled(true);
		}
	}
	
}
