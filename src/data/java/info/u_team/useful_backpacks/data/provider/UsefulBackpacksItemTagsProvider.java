package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.*;
import static info.u_team.useful_backpacks.init.UsefulBackpacksTags.Items.*;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;

public class UsefulBackpacksItemTagsProvider extends CommonItemTagsProvider {
	
	public UsefulBackpacksItemTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		getBuilder(BACKPACK).add(SMALL_BACKPACK.get(), MEDIUM_BACKPACK.get(), LARGE_BACKPACK.get(), ENDERCHEST_BACKPACK.get());
		getBuilder(FILTER).add(ITEM_FILTER.get(), TAG_FILTER.get());
		
		getBuilder(TagUtil.createItemTag("curios", "back")).add(BACKPACK);
	}
	
}