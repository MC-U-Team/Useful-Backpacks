package info.u_team.useful_backpacks.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

public class CommonConfig {
	
	public static final ForgeConfigSpec CONFIG;
	private static final CommonConfig INSTANCE;
	
	static {
		Pair<CommonConfig, ForgeConfigSpec> pair = new Builder().configure(CommonConfig::new);
		CONFIG = pair.getRight();
		INSTANCE = pair.getLeft();
	}
	
	public static CommonConfig getInstance() {
		return INSTANCE;
	}
	
	public final BooleanValue allowStackingBackpacks;
	
	private CommonConfig(Builder builder) {
		builder.comment("Common configuration settings").push("common");
		allowStackingBackpacks = builder.comment("This option controls if backpacks in backpacks are allowed.", "If set to true you can put backpacks in existing backpacks and stack them together.", "If set to false you cannot put backpacks in backpacks").define("allowStackingBackpacks", true);
		builder.pop();
	}
	
}
