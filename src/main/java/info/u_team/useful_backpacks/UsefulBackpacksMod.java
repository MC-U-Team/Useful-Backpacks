package info.u_team.useful_backpacks;

import info.u_team.useful_backpacks.config.ServerConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod(UsefulBackpacksMod.MODID)
public class UsefulBackpacksMod {
	
	public static final String MODID = "usefulbackpacks";
	
	public UsefulBackpacksMod() {
		ModLoadingContext.get().registerConfig(Type.SERVER, ServerConfig.CONFIG);
	}
	
}
