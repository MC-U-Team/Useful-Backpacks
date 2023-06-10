package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.config.ForgeCommonConfig;
import info.u_team.useful_backpacks.event.ItemPickupEventHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

@Construct(modid = UsefulBackpacksReference.MODID)
public class UsefulBackpacksForgeCommonConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ForgeCommonConfig.CONFIG);
		
		BusRegister.registerForge(ItemPickupEventHandler::registerForge);
	}
}