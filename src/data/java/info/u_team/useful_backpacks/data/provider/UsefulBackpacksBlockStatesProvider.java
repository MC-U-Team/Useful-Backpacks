package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksBlocks.FILTER_CONFIGURATOR;

import info.u_team.u_team_core.data.CommonBlockStatesProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;

public class UsefulBackpacksBlockStatesProvider extends CommonBlockStatesProvider {
	
	public UsefulBackpacksBlockStatesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		simpleBlock(FILTER_CONFIGURATOR.get(), cubeBottomTop(FILTER_CONFIGURATOR.get()));
	}
	
	private ModelFile cubeBottomTop(Block block) {
		final ResourceLocation location = blockTexture(block);
		return models().cubeBottomTop(getPath(FILTER_CONFIGURATOR.get()), extend(location, "_side"), extend(location, "_bottom"), extend(location, "_top"));
	}
	
	private ResourceLocation extend(ResourceLocation location, String suffix) {
		return new ResourceLocation(location.getNamespace(), location.getPath() + suffix);
	}
	
}
