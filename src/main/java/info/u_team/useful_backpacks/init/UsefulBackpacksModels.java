package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.ModelUtil;
import info.u_team.useful_backpacks.menu.slot.BackpackFilterSlot;
import info.u_team.useful_backpacks.menu.slot.FilterSlot;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelEvent.RegisterGeometryLoaders;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulBackpacksModels {
	
	private static void modelRegistry(RegisterGeometryLoaders event) {
		ModelUtil.addTexture(ForgeHooksClient.getBlockMaterial(BackpackFilterSlot.BACKGROUND));
		ModelUtil.addTexture(ForgeHooksClient.getBlockMaterial(FilterSlot.BACKGROUND));
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulBackpacksModels::modelRegistry);
	}
	
}
