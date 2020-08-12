package info.u_team.useful_backpacks.integration.curios.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class CuriosIntegrationCapabilities {
	
	private static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
		
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addGenericListener(ItemStack.class, CuriosIntegrationCapabilities::onAttachCapabilities);
	}
	
}
