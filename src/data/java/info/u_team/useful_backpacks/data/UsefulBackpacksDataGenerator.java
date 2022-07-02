package info.u_team.useful_backpacks.data;

import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksBlockStatesProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksItemModelsProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksItemTagsProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksLanguagesProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksLootTablesProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksRecipesProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD)
public class UsefulBackpacksDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final GenerationData data = new GenerationData(UsefulBackpacksMod.MODID, event);
		data.addProvider(event.includeServer(), UsefulBackpacksItemTagsProvider::new);
		data.addProvider(event.includeServer(), UsefulBackpacksLootTablesProvider::new);
		data.addProvider(event.includeServer(), UsefulBackpacksRecipesProvider::new);
		
		data.addProvider(event.includeClient(), UsefulBackpacksBlockStatesProvider::new);
		data.addProvider(event.includeClient(), UsefulBackpacksItemModelsProvider::new);
		data.addProvider(event.includeClient(), UsefulBackpacksLanguagesProvider::new);
	}
}
