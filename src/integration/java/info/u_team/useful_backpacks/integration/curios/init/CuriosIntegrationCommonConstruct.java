package info.u_team.useful_backpacks.integration.curios.init;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import info.u_team.u_team_core.api.integration.IModIntegration;
import info.u_team.u_team_core.api.integration.Integration;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.event.ItemPickupEventHandler;
import info.u_team.useful_backpacks.integration.curios.util.BackpackCuriosUtil;
import net.minecraft.item.ItemStack;

@Integration(modid = UsefulBackpacksMod.MODID, integration = "curios")
public class CuriosIntegrationCommonConstruct implements IModIntegration {
	
	@Override
	public void construct() {
		BusRegister.registerMod(CuriosIntegrationModComms::registerMod);
		BusRegister.registerMod(CuriosIntegrationNetwork::registerMod);
		
		BusRegister.registerForge(CuriosIntegrationCapabilities::registerForge);
		
		ItemPickupEventHandler.INTEGRATION_BACKPACKS.add((player) -> {
			final Optional<ImmutableTriple<String, Integer, ItemStack>> optional = BackpackCuriosUtil.getBackpack(player);
			if (optional.isPresent()) {
				return optional.get().getRight();
			}
			return ItemStack.EMPTY;
		});
	}
	
}
