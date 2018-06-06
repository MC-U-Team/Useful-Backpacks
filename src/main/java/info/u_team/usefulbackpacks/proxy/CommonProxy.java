package info.u_team.usefulbackpacks.proxy;

import info.u_team.u_team_core.util.registry.CommonRegistry;
import info.u_team.usefulbackpacks.UsefulBackpacksMod;
import info.u_team.usefulbackpacks.crafting.UsefulBackPacksCrafting;
import info.u_team.usefulbackpacks.handler.UsefulBackPacksGuiHandler;
import info.u_team.usefulbackpacks.item.UsefulBackPacksItems;
import info.u_team.usefulbackpacks.tab.UsefulBackPacksTabs;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent event) {
		new UsefulBackPacksTabs();
		new UsefulBackPacksItems();
	}
	
	public void init(FMLInitializationEvent event) {
		new UsefulBackPacksCrafting();
		CommonRegistry.registerGuiHandler(UsefulBackpacksMod.getInstance(), new UsefulBackPacksGuiHandler());
	}
	
	public void postinit(FMLPostInitializationEvent event) {
	}
	
}
