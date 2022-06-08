package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ENDERCHEST_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ITEM_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.LARGE_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.MEDIUM_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.SMALL_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.TAG_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksTags.Items.BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksTags.Items.FILTER;

import info.u_team.u_team_core.data.GenerationData;
import info.u_team.u_team_core.util.TagUtil;
import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;

public class UsefulBackpacksItemTagsProvider extends TagsProvider<Item> {
	
	@SuppressWarnings("deprecation")
	public UsefulBackpacksItemTagsProvider(GenerationData data) {
		super(data.getGenerator(), Registry.ITEM, data.getModid(), data.getExistingFileHelper());
	}
	
	@Override
	protected void addTags() {
		tag(BACKPACK).add(SMALL_BACKPACK.get(), MEDIUM_BACKPACK.get(), LARGE_BACKPACK.get(), ENDERCHEST_BACKPACK.get());
		tag(FILTER).add(ITEM_FILTER.get(), TAG_FILTER.get());
		
		tag(TagUtil.createItemTag("curios", "back")).addTag(BACKPACK);
	}
	
}