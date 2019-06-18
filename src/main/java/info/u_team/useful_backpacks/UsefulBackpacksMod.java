package info.u_team.useful_backpacks;

import info.u_team.u_team_core.api.IModProxy;
import info.u_team.useful_backpacks.proxy.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UsefulBackpacksMod.modid)
public class UsefulBackpacksMod {
	
	public static final String modid = "usefulbackpacks";
	
	public static final IModProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	
	public UsefulBackpacksMod() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		proxy.construct();
	}
	
	@SubscribeEvent
	public void setup(FMLCommonSetupEvent event) {
		proxy.setup();
	}
	
	@SubscribeEvent
	public void ready(FMLLoadCompleteEvent event) {
		proxy.complete();
	}
	
}
