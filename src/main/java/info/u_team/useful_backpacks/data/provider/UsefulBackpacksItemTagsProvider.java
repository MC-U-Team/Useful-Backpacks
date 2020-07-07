package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.*;
import static info.u_team.useful_backpacks.init.UsefulBackpacksTags.Items.BACKPACK;

import info.u_team.u_team_core.data.*;

public class UsefulBackpacksItemTagsProvider extends CommonItemTagsProvider {
	
	public UsefulBackpacksItemTagsProvider(GenerationData data, UsefulBackpacksBlockTagsProvider blockTags) {
		super(data, blockTags);
	}
	
	@Override
	protected void registerTags() {
		getBuilder(BACKPACK).func_240534_a_(SMALL_BACKPACK.get(), MEDIUM_BACKPACK.get(), LARGE_BACKPACK.get(), ENDERCHEST_BACKPACK.get());
	}
	
}