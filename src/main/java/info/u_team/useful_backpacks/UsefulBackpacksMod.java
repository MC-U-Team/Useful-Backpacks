package info.u_team.useful_backpacks;

import info.u_team.u_team_core.integration.IntegrationManager;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.u_team_core.util.verify.JarSignVerifier;
import info.u_team.useful_backpacks.config.ServerConfig;
import info.u_team.useful_backpacks.init.*;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod(UsefulBackpacksMod.MODID)
public class UsefulBackpacksMod {
	
	public static final String MODID = "usefulbackpacks";
	
	public UsefulBackpacksMod() {
		JarSignVerifier.checkSigned(MODID);
		ModLoadingContext.get().registerConfig(Type.SERVER, ServerConfig.CONFIG);
		IntegrationManager.constructIntegrations(MODID);
		register();
	}
	
	private void register() {
		BusRegister.registerMod(UsefulBackpacksContainerTypes::register);
		BusRegister.registerMod(UsefulBackpacksItems::register);
		BusRegister.registerMod(UsefulBackpacksRecipeSerializers::register);
	}
	
}
