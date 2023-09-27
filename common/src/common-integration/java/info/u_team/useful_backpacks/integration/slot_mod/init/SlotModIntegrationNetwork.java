package info.u_team.useful_backpacks.integration.slot_mod.init;

import java.util.Optional;

import info.u_team.u_team_core.api.network.NetworkEnvironment;
import info.u_team.u_team_core.api.network.NetworkHandler;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.integration.slot_mod.message.OpenBackpackMessage;
import net.minecraft.resources.ResourceLocation;

public class SlotModIntegrationNetwork {
	
	public static final NetworkHandler NETWORK = NetworkHandler.create(0, new ResourceLocation(UsefulBackpacksReference.MODID, "slot_mod"));
	
	public static void register() {
		NETWORK.registerMessage(0, OpenBackpackMessage.class, OpenBackpackMessage::encode, OpenBackpackMessage::decode, OpenBackpackMessage.Handler::handle, Optional.of(NetworkEnvironment.SERVER));
	}
	
}
