package info.u_team.useful_backpacks.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

public class ServerConfig {
	
	public static final ForgeConfigSpec CONFIG;
	private static final ServerConfig INSTANCE;
	
	static {
		Pair<ServerConfig, ForgeConfigSpec> pair = new Builder().configure(ServerConfig::new);
		CONFIG = pair.getRight();
		INSTANCE = pair.getLeft();
	}
	
	public static ServerConfig getInstance() {
		return INSTANCE;
	}
	
	public final BooleanValue shareAllNBTData;
	
	private ServerConfig(Builder builder) {
		builder.comment("Server configuration settings").push("server");
		shareAllNBTData = builder.comment("This option controlls the behaviour of the nbt synchronization between the server and the client on a dedicated server.", "If set to true this mod sync all inventory data to the client which may lead to very large nbt packets or even timeouts when the nbt data is > 2mb.", "If set to false this fix this issue but don't let you use the item in creative mod.", "If you set this to false DO NOT CHANGE YOUR GAMEMODE to creative as this will clear the inventory data!").define("shareAllNBTData", true);
		builder.pop();
	}
	
}
