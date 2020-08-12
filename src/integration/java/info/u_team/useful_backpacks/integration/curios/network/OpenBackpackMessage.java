package info.u_team.useful_backpacks.integration.curios.network;

import java.util.function.Supplier;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class OpenBackpackMessage {
	
	public static void encode(OpenBackpackMessage message, PacketBuffer sendBuffer) {
	}
	
	public static OpenBackpackMessage decode(PacketBuffer sendBuffer) {
		return new OpenBackpackMessage();
	}
	
	public static class Handler {
		
		public static void handle(OpenBackpackMessage message, Supplier<Context> contextSupplier) {
			final Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				final ServerPlayerEntity player = context.getSender();
			});
			context.setPacketHandled(true);
		}
	}
	
}
