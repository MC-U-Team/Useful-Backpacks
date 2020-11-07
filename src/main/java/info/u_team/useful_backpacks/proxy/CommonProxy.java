package info.u_team.useful_backpacks.proxy;

import info.u_team.u_team_core.registry.CommonRegistry;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.handler.UsefulBackPacksGuiHandler;
import info.u_team.useful_backpacks.init.*;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent event) {
		UsefulBackPacksItems.preinit();
	}
	
	public void init(FMLInitializationEvent event) {
		UsefulBackPacksCreativeTabs.init();
		UsefulBackPacksRecipes.init();
		
		CommonRegistry.registerGuiHandler(UsefulBackpacksMod.getInstance(), new UsefulBackPacksGuiHandler());
	}
	
	public void postinit(FMLPostInitializationEvent event) {
	}
	
}
