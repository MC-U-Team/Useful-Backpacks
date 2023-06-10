package info.u_team.useful_backpacks.config;

import info.u_team.u_team_core.util.ServiceUtil;
import info.u_team.useful_backpacks.util.ConfigValueHolder;

public abstract class CommonConfig {
	
	private static final CommonConfig INSTANCE = ServiceUtil.loadOne(CommonConfig.class);
	
	public static CommonConfig getInstance() {
		return INSTANCE;
	}
	
	public abstract ConfigValueHolder<Boolean> allowStackingBackpacks();
	
}
