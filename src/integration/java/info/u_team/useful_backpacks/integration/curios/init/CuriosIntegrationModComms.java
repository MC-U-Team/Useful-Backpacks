package info.u_team.useful_backpacks.integration.curios.init;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.*;

public class CuriosIntegrationModComms {
	
	private static void enqueue(InterModEnqueueEvent event) {
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().build());
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(CuriosIntegrationModComms::enqueue);
	}
	
}
