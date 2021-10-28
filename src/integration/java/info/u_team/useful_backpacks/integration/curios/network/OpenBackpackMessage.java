package info.u_team.useful_backpacks.integration.curios.network;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.integration.curios.util.BackpackCuriosUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class OpenBackpackMessage {
	
	public static void encode(OpenBackpackMessage message, FriendlyByteBuf sendBuffer) {
	}
	
	public static OpenBackpackMessage decode(FriendlyByteBuf sendBuffer) {
		return new OpenBackpackMessage();
	}
	
	public static class Handler {
		
		public static void handle(OpenBackpackMessage message, Supplier<Context> contextSupplier) {
			final Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				final ServerPlayer player = context.getSender();
				final Optional<ItemStack> curioBackpack = BackpackCuriosUtil.getBackpack(player).map(ImmutableTriple::getRight).filter(stack -> stack.getItem() instanceof IBackpack);
				if (curioBackpack.isPresent()) {
					final ItemStack stack = curioBackpack.get();
					final IBackpack backpack = (IBackpack) stack.getItem();
					backpack.open(player, stack, -2);
				}
			});
			context.setPacketHandled(true);
		}
	}
	
}
