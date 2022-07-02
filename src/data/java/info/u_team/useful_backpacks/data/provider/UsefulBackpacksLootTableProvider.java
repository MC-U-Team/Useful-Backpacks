package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksBlocks.FILTER_CONFIGURATOR;

import java.util.function.BiConsumer;

import info.u_team.u_team_core.data.CommonLootTableProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class UsefulBackpacksLootTableProvider extends CommonLootTableProvider {
	
	public UsefulBackpacksLootTableProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register(BiConsumer<ResourceLocation, LootTable> consumer) {
		registerBlock(FILTER_CONFIGURATOR, addBasicBlockLootTable(FILTER_CONFIGURATOR.get()), consumer);
	}
	
}
