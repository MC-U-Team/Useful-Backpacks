package info.u_team.useful_backpacks.integration.curios.init;

import info.u_team.u_team_core.api.event.CommonEvents;
import info.u_team.u_team_core.api.integration.Integration;
import info.u_team.u_team_core.api.integration.ModIntegration;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.integration.slot_mod.init.SlotModIntegrationClientEvents;
import info.u_team.useful_backpacks.integration.slot_mod.init.SlotModIntegrationKeyMappings;
import info.u_team.useful_backpacks.integration.slot_mod.util.BackpackSlotModUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.settings.IKeyConflictContext;

@Integration(modid = UsefulBackpacksMod.MODID, integration = "curios", client = true)
public class CuriosIntegrationClientConstruct implements ModIntegration {
	
	@Override
	public void construct() {
		SlotModIntegrationKeyMappings.register();
		SlotModIntegrationClientEvents.register();
		
		BusRegister.registerMod(CuriosIntegrationRenderers::registerMod);
		
		CommonEvents.registerSetup(() -> {
			SlotModIntegrationKeyMappings.OPEN_BACKPACK.get().setKeyConflictContext(new IKeyConflictContext() {
				
				@Override
				public boolean isActive() {
					return BackpackSlotModUtil.findBackpack(Minecraft.getInstance().player).isPresent();
				}
				
				@Override
				public boolean conflicts(IKeyConflictContext other) {
					return false;
				}
			});
		});
	}
	
}
