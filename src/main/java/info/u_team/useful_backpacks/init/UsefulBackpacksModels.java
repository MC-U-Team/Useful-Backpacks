package info.u_team.useful_backpacks.init;

import net.minecraftforge.client.event.ModelEvent.RegisterGeometryLoaders;
import net.minecraftforge.eventbus.api.IEventBus;

@Deprecated
public class UsefulBackpacksModels {
	
	// TODO remove, should not be useful any more
	private static void modelRegistry(RegisterGeometryLoaders event) {
		// ModelUtil.addTexture(ForgeHooksClient.getBlockMaterial(BackpackFilterSlot.BACKGROUND));
		// ModelUtil.addTexture(ForgeHooksClient.getBlockMaterial(FilterSlot.BACKGROUND));
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulBackpacksModels::modelRegistry);
	}
	
}
