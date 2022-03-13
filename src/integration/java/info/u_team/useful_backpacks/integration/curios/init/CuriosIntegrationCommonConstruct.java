package info.u_team.useful_backpacks.integration.curios.init;

import info.u_team.u_team_core.api.integration.Integration;
import info.u_team.u_team_core.api.integration.ModIntegration;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.event.ItemPickupEventHandler;
import info.u_team.useful_backpacks.integration.curios.util.BackpackCuriosUtil;
import net.minecraft.world.item.ItemStack;

@Integration(modid = UsefulBackpacksMod.MODID, integration = "curios")
public class CuriosIntegrationCommonConstruct implements ModIntegration {
	
	@Override
	public void construct() {
		BusRegister.registerMod(CuriosIntegrationModComms::registerMod);
		BusRegister.registerMod(CuriosIntegrationNetwork::registerMod);
		
		BusRegister.registerForge(CuriosIntegrationCapabilities::registerForge);
		
		ItemPickupEventHandler.INTEGRATION_BACKPACKS.add((player) -> {
			final var slotResult = BackpackCuriosUtil.getBackpack(player);
			if (slotResult.isPresent()) {
				return slotResult.get().stack();
			}
			return ItemStack.EMPTY;
		});
	}
	
}
