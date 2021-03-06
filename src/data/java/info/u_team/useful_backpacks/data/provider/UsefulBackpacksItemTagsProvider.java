package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ENDERCHEST_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ITEM_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.LARGE_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.MEDIUM_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.SMALL_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.TAG_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksTags.Items.BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksTags.Items.FILTER;

import info.u_team.u_team_core.data.CommonItemTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
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