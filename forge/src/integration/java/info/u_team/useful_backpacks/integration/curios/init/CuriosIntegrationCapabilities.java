package info.u_team.useful_backpacks.integration.curios.init;

import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.integration.curios.capability.CuriosBackpackCapability;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosIntegrationCapabilities {
	
	private static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
		final ItemStack stack = event.getObject();
		if (!(stack.getItem() instanceof Backpack)) {
			return;
		}
		
		event.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider() {
			
			final LazyOptional<ICurio> curioCapability = LazyOptional.of(() -> new CuriosBackpackCapability(stack));
			
			@Override
			public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
				return CuriosCapability.ITEM.orEmpty(capability, curioCapability);
			}
		});
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addGenericListener(ItemStack.class, CuriosIntegrationCapabilities::onAttachCapabilities);
	}
	
}
