package info.u_team.useful_backpacks.proxy;

import info.u_team.useful_backpacks.init.UsefulBackPacksColors;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public void preinit(FMLPreInitializationEvent event) {
		super.preinit(event);
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		UsefulBackPacksColors.init();
	}
	
	@Override
	public void postinit(FMLPostInitializationEvent event) {
		super.postinit(event);
	}
}
