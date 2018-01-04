package info.u_team.usefulbackpacks;

import info.u_team.u_team_core.sub.USubMod;
import info.u_team.usefulbackpacks.proxy.CommonProxy;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = UsefulBackPacksConstants.MODID, name = UsefulBackPacksConstants.NAME, version = UsefulBackPacksConstants.VERSION, acceptedMinecraftVersions = UsefulBackPacksConstants.MCVERSION, dependencies = UsefulBackPacksConstants.DEPENDENCIES)
public class UsefulBackpacksMod extends USubMod {
	
	public UsefulBackpacksMod() {
		super(UsefulBackPacksConstants.MODID, UsefulBackPacksConstants.NAME, UsefulBackPacksConstants.VERSION, UsefulBackPacksConstants.UPDATEURL);
	}
	
	@Instance
	private static UsefulBackpacksMod instance;
	
	@SidedProxy(serverSide = UsefulBackPacksConstants.COMMONPROXY, clientSide = UsefulBackPacksConstants.CLIENTPROXY)
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
