package info.u_team.useful_backpacks;

import info.u_team.u_team_core.util.annotation.AnnotationManager;
import net.fabricmc.api.ModInitializer;

public class UsefulBackpacksMod implements ModInitializer {
	
	public static final String MODID = UsefulBackpacksReference.MODID;
	
	@Override
	public void onInitialize() {
		AnnotationManager.callAnnotations(MODID);
	}
}
