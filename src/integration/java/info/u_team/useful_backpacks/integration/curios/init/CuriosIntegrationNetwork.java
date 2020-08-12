package info.u_team.useful_backpacks.integration.curios.init;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class CuriosIntegrationNetwork {
	
	public static final String PROTOCOL = "1.16.1-1";
	
	public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(new ResourceLocation(UsefulBackpacksMod.MODID, "curios"), () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);
	
	public static void setup(FMLCommonSetupEvent event) {
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(CuriosIntegrationNetwork::setup);
	}
	
}
