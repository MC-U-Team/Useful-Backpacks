package info.u_team.usefulbackpacks;

import info.u_team.u_team_core.UTeamCoreMod;
import info.u_team.u_team_core.mod.UTeamCoreReference;
import info.u_team.usefulbackpacks.crafting.ModCrafting;
import info.u_team.usefulbackpacks.creativetab.ModCreativeTabs;
import info.u_team.usefulbackpacks.handler.GuiHandler;
import info.u_team.usefulbackpacks.item.ModItems;
import info.u_team.usefulbackpacks.mod.MetadataFetcher;
import info.u_team.usefulbackpacks.proxy.CommonProxy;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = Reference.modid, name = Reference.name, version = Reference.version, acceptedMinecraftVersions = Reference.mcversion, dependencies = Reference.dependencies)
public class ModMain {
	
	private static UTeamCoreMod core = UTeamCoreReference.instance;
	
	@Instance(value = Reference.modid)
	private static ModMain instance;
	
	@SidedProxy(serverSide = "info.u_team.usefulbackpacks.proxy.ServerProxy", clientSide = "info.u_team.usefulbackpacks.proxy.ClientProxy", modId = Reference.modid)
	private static CommonProxy proxy;
	
	private ModCreativeTabs creativetabs;
	private ModItems items;
	private ModCrafting crafting;
	
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
		crafting = new ModCrafting();
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		proxy.registerColors();
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
	
	public ModCrafting getCrafting() {
		return crafting;
	}
	
}
