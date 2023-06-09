package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksBlocks.FILTER_CONFIGURATOR;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ENDERCHEST_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ITEM_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.LARGE_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.MEDIUM_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.SMALL_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.TAG_FILTER;

import info.u_team.u_team_core.data.CommonItemModelProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

public class UsefulBackpacksItemModelProvider extends CommonItemModelProvider {
	
	public UsefulBackpacksItemModelProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register() {
		simpleBackpackGenerated(SMALL_BACKPACK.get());
		simpleBackpackGenerated(MEDIUM_BACKPACK.get());
		simpleBackpackGenerated(LARGE_BACKPACK.get());
		simpleGenerated(ENDERCHEST_BACKPACK.get());
		simpleGenerated(ITEM_FILTER.get());
		simpleGenerated(TAG_FILTER.get());
		
		simpleBlock(FILTER_CONFIGURATOR.get());
	}
	
	protected void simpleBackpackGenerated(BackpackItem item) {
		final String registryPath = getPath(item);
		getBuilder(registryPath).parent(new UncheckedModelFile("item/generated")).texture("layer0", "item/backpack/" + item.getBackpack().getName());
	}
	
}
