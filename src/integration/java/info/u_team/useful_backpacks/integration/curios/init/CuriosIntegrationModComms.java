package info.u_team.useful_backpacks.integration.curios.init;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

public class CuriosIntegrationModComms {
	
	private static void enqueue(InterModEnqueueEvent event) {
		InterModComms.sendTo(CuriosAPI.MODID, CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("back"));
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(CuriosIntegrationModComms::enqueue);
	}
	
}
