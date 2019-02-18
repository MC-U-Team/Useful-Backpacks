package info.u_team.useful_backpacks.proxy;

import info.u_team.useful_backpacks.init.*;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy implements IModProxy {
	
	@Override
	public void construct() {
		super.construct();
		UsefulBackPacksGuis.construct();
	}
	
	@Override
	public void setup() {
		super.setup();
	}
	
	@Override
	public void complete() {
		super.complete();
		UsefulBackPacksColors.complete();
	}
}
