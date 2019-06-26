package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.gui.BackpackScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class UsefulBackpacksGuis {
	
	public static void construct() {
		ScreenManager.registerFactory(UsefulBackpacksContainers.TYPE, BackpackScreen::new);
	}
}
