package info.u_team.useful_backpacks;

import info.u_team.u_team_core.util.verify.JarSignVerifier;
import info.u_team.useful_backpacks.config.ServerConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod(UsefulBackpacksMod.MODID)
public class UsefulBackpacksMod {
	
	public static final String MODID = "usefulbackpacks";
	
	public UsefulBackpacksMod() {
		JarSignVerifier.checkSigned(MODID);
		ModLoadingContext.get().registerConfig(Type.SERVER, ServerConfig.CONFIG);
	}
	
}
