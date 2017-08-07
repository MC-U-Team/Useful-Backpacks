package info.u_team.usefullbackpacks;

import info.u_team.u_team_core.UTeamCoreMod;
import info.u_team.u_team_core.mod.UTeamCoreReference;
import info.u_team.usefullbackpacks.creativetab.ModCreativeTabs;
import info.u_team.usefullbackpacks.handler.GuiHandler;
import info.u_team.usefullbackpacks.item.ModItems;
import info.u_team.usefullbackpacks.mod.MetadataFetcher;
import info.u_team.usefullbackpacks.proxy.CommonProxy;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = Reference.modid, name = Reference.name, version = Reference.version, acceptedMinecraftVersions = Reference.mcversion, dependencies = Reference.dependencies)
public class ModMain {
	
	private static UTeamCoreMod core = UTeamCoreReference.instance;
	
	@Instance(value = Reference.modid)
	private static ModMain instance;
	
	@SidedProxy(serverSide = "info.u_team.usefullbackpacks.proxy.ServerProxy", clientSide = "info.u_team.usefullbackpacks.proxy.ClientProxy", modId = Reference.modid)
	private static CommonProxy proxy;
	
	private ModCreativeTabs creativetabs;
	private ModItems items;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		new MetadataFetcher().applyMetadata(event.getModMetadata());
		creativetabs = new ModCreativeTabs();
		items = new ModItems();
		proxy.registerModels();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		core.getCommonRegistry().registerGuiHandler(this, new GuiHandler());
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		
	}
	
	public static ModMain getInstance() {
		return instance;
	}
	
	public static UTeamCoreMod getCore() {
		return core;
	}
	
	public static CommonProxy getProxy() {
		return proxy;
	}
	
	public ModCreativeTabs getCreativeTabs() {
		return creativetabs;
	}
	
	public ModItems getItems() {
		return items;
	}
	
}
