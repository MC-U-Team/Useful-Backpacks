package info.u_team.useful_backpacks.integration.curios;

import info.u_team.u_team_core.api.integration.*;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.integration.curios.init.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.*;

@Integration("curios")
public class CuriosIntegration implements IModIntegration {
	
	@SubscribeEvent
	public static void enqueue(InterModEnqueueEvent event) {
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().build());
	}
	
	@Override
	public void construct() {
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> BusRegister.registerMod(CuriosIntegrationKeys::register));
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> BusRegister.registerMod(CuriosIntegrationModComms::register));
	}
}
