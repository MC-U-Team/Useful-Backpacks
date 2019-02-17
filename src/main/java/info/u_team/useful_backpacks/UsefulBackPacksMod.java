package info.u_team.useful_backpacks;

import info.u_team.useful_backpacks.proxy.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UsefulBackPacksMod.modid)
public class UsefulBackPacksMod {
	
	public static final String modid = "usefulbackpacks";
	
	public UsefulBackPacksMod() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		CommonProxy.construct();
	}
	
	@SubscribeEvent
	public void common(FMLCommonSetupEvent event) {
		CommonProxy.setup();
	}
	
	@SubscribeEvent
	public void client(final FMLClientSetupEvent event) {
		ClientProxy.setup();
	}
	
}
