package info.u_team.useful_backpacks.integration.curios;

import info.u_team.u_team_core.api.integration.*;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.integration.curios.init.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

@Integration("curios")
public class CuriosIntegration implements IModIntegration {
	
	@Override
	public void construct() {
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> BusRegister.registerMod(CuriosIntegrationKeys::register));
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> BusRegister.registerMod(CuriosIntegrationModComms::register));
	}
}
