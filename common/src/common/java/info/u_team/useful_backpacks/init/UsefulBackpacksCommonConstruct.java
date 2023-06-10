package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.useful_backpacks.UsefulBackpacksReference;

@Construct(modid = UsefulBackpacksReference.MODID)
public class UsefulBackpacksCommonConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		//ModLoadingContext.get().registerConfig(Type.COMMON, ForgeCommonConfig.CONFIG);
		
		UsefulBackpacksBlocks.register();
		UsefulBackpacksCreativeTabs.registerMod();
		UsefulBackpacksMenuTypes.register();
		UsefulBackpacksItems.register();
		UsefulBackpacksRecipeSerializers.register();
		
		// BusRegister.registerForge(ItemPickupEventHandler::registerForge);
	}
}