package info.u_team.usefulbackpacks;

import static info.u_team.usefulbackpacks.UsefulBackPacksConstants.*;

import info.u_team.u_team_core.sub.USubMod;
import info.u_team.usefulbackpacks.proxy.CommonProxy;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = MODID, name = NAME, version = VERSION, acceptedMinecraftVersions = MCVERSION, dependencies = DEPENDENCIES, updateJSON = UPDATEURL)
public class UsefulBackpacksMod extends USubMod {
	
	public UsefulBackpacksMod() {
		super();
	}
	
	@Instance
	private static UsefulBackpacksMod instance;
	
	@SidedProxy(serverSide = COMMONPROXY, clientSide = CLIENTPROXY)
	private static CommonProxy proxy;
	
	public static UsefulBackpacksMod getInstance() {
		return instance;
	}
	
	public CommonProxy getProxy() {
		return proxy;
	}
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		super.preinit(event);
		proxy.preinit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
		proxy.init(event);
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		super.postinit(event);
		proxy.postinit(event);
	}
	
}