package info.u_team.useful_backpacks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsefulBackPacksConstants {
	
	public static final String MODID = "usefulbackpacks";
	public static final String NAME = "Useful Backpacks";
	public static final String VERSION = "${version}";
	public static final String MCVERSION = "${mcversion}";
	public static final String DEPENDENCIES = "required:forge@[14.23.5.2768,);required-after:uteamcore@[2.2.5.147,);";
	public static final String UPDATEURL = "https://api.u-team.info/update/usefulbackpacks.json";
	
	public static final String COMMONPROXY = "info.u_team.useful_backpacks.proxy.CommonProxy";
	public static final String CLIENTPROXY = "info.u_team.useful_backpacks.proxy.ClientProxy";
	
	public static final Logger LOGGER = LogManager.getLogger(NAME);
	
	private UsefulBackPacksConstants() {
	}
	
}