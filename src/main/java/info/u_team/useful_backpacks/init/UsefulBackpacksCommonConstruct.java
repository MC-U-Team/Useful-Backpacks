package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.config.CommonConfig;
import info.u_team.useful_backpacks.config.ServerConfig;
import info.u_team.useful_backpacks.event.ItemPickupEventHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

@Construct(modid = UsefulBackpacksMod.MODID)
public class UsefulBackpacksCommonConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.CONFIG);
		ModLoadingContext.get().registerConfig(Type.SERVER, ServerConfig.CONFIG);
		
		BusRegister.registerMod(UsefulBackpacksBlocks::registerMod);
		BusRegister.registerMod(UsefulBackpacksMenuTypes::registerMod);
		BusRegister.registerMod(UsefulBackpacksItems::registerMod);
		BusRegister.registerMod(UsefulBackpacksRecipeSerializers::registerMod);
		
		BusRegister.registerForge(ItemPickupEventHandler::registerForge);
	}
}