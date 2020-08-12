package info.u_team.useful_backpacks.integration.curios.init;

import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.integration.curios.capability.CuriosBackpackCapability;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosIntegrationCapabilities {
	
	private static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
		final ItemStack stack = event.getObject();
		if (!(stack.getItem() instanceof IBackpack)) {
			return;
		}
		
		final LazyOptional<ICurio> curioCapability = LazyOptional.of(() -> new CuriosBackpackCapability(stack));
		
		event.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider() {
			
			@Override
			public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
				return CuriosCapability.ITEM.orEmpty(capability, curioCapability);
			}
		});
		
		event.addListener(() -> {
			System.out.println("SHUOLD CALL WHEN ITEM IS INVALIDETED: " + stack);
		});
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addGenericListener(ItemStack.class, CuriosIntegrationCapabilities::onAttachCapabilities);
	}
	
}
