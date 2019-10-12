package info.u_team.useful_backpacks.config;

import info.u_team.useful_backpacks.UsefulBackPacksConstants;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

@Config(modid = UsefulBackPacksConstants.MODID, category = "server")
public class ServerConfig {
	
	@Comment(value = { "This option controlls the behaviour of the nbt synchronization between the server and the client on a dedicated server.", "If set to true this mod sync all inventory data to the client which may lead to very large nbt packets or even timeouts when the nbt data is > 2mb.", "If set to false this fix this issue but don't let you use the item in creative mod.", "If you set this to false DO NOT CHANGE YOUR GAMEMODE to creative as this will clear the inventory data!" })
	@Name("shareAllNBTData")
	public static boolean shareAllNBTData = true;
	
}