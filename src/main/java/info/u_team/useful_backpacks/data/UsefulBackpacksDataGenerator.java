package info.u_team.useful_backpacks.data;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.data.provider.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD)
public class UsefulBackpacksDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			generator.addProvider(new UsefulBackpacksItemTagsProvider(generator)); // Generate item tags
			
			generator.addProvider(new UsefulBackpacksRecipesProvider(generator)); // Generate recipes
		}
		if (event.includeClient()) {
			generator.addProvider(new UsefulBackpacksItemModelsProvider(generator, UsefulBackpacksMod.MODID, event.getExistingFileHelper()));
		}
	}
	
}
