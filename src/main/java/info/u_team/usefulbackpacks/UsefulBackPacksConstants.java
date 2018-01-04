package info.u_team.usefulbackpacks;

import org.apache.logging.log4j.*;

public class UsefulBackPacksConstants {
	
	public static final String MODID = "usefulbackpacks";
	public static final String NAME = "Useful Backpacks";
	public static final String VERSION = "1.0.0";
	public static final String MCVERSION = "1.10.2";
	public static final String DEPENDENCIES = "required-after:uteamcore@[1.0.1,);";
	public static final String UPDATEURL = "https://api.u-team.info/update/usefulbackpacks.json";
	
	public static final String COMMONPROXY = "info.u_team.usefulbackpacks.proxy.CommonProxy";
	public static final String CLIENTPROXY = "info.u_team.usefulbackpacks.proxy.ClientProxy";
	
	public static final Logger LOGGER = LogManager.getLogger(NAME);
	
}
