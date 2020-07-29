package info.u_team.useful_backpacks.integration.curious.data;

import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.integration.curious.data.provider.CuriousIntegrationItemTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD)
public class CuriousIntegrationDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final GenerationData data = new GenerationData(UsefulBackpacksMod.MODID, event);
		if (event.includeServer()) {
			data.addProvider(CuriousIntegrationItemTagsProvider::new);
		}
	}
	
}
