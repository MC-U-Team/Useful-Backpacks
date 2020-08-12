package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.*;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.config.ServerConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

@Construct(modid = UsefulBackpacksMod.MODID)
public class UsefulBackpacksCommonConstruct implements IModConstruct {
	
	@Override
	public void construct() {
		ModLoadingContext.get().registerConfig(Type.SERVER, ServerConfig.CONFIG);
		
		BusRegister.registerMod(UsefulBackpacksContainerTypes::register);
		BusRegister.registerMod(UsefulBackpacksItems::register);
		BusRegister.registerMod(UsefulBackpacksRecipeSerializers::register);
	}
	
}