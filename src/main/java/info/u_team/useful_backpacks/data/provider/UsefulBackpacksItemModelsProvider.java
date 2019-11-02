package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.*;

import info.u_team.u_team_core.data.CommonItemModelsProvider;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

public class UsefulBackpacksItemModelsProvider extends CommonItemModelsProvider {
	
	public UsefulBackpacksItemModelsProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
		super(generator, modid, existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
		simpleBackpackGenerated(SMALL_BACKPACK);
		simpleBackpackGenerated(MEDIUM_BACKPACK);
		simpleBackpackGenerated(LARGE_BACKPACK);
		simpleGenerated(ENDERCHEST_BACKPACK);
	}
	
	protected void simpleBackpackGenerated(BackpackItem item) {
		final String registryPath = item.getRegistryName().getPath();
		getBuilder(registryPath).parent(new UncheckedModelFile("item/generated")).texture("layer0", "item/backpack/" + item.getBackpack().getName());
	}
	
}
