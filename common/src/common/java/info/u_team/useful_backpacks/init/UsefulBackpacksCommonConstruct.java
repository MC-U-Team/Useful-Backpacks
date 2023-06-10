package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.config.CommonConfig;
import info.u_team.useful_backpacks.config.ServerConfig;
import info.u_team.useful_backpacks.event.ItemPickupEventHandler;
import net.minecraftforge.fml.ModLoadingContext;

@Construct(modid = UsefulBackpacksMod.MODID)
public class UsefulBackpacksCommonConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.CONFIG);
		ModLoadingContext.get().registerConfig(Type.SERVER, ServerConfig.CONFIG);
		
		UsefulBackpacksBlocks.register();
		UsefulBackpacksCreativeTabs.registerMod();
		UsefulBackpacksMenuTypes.register();
		UsefulBackpacksItems.register();
		UsefulBackpacksRecipeSerializers.register();
		
		BusRegister.registerForge(ItemPickupEventHandler::registerForge);
	}
}