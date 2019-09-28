package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksTags.Items.BACKPACK;

import info.u_team.u_team_core.data.CommonItemTagsProvider;
import info.u_team.useful_backpacks.init.UsefulBackpacksItems;
import net.minecraft.data.DataGenerator;

public class UsefulBackpacksItemTagsProvider extends CommonItemTagsProvider {
	
	public UsefulBackpacksItemTagsProvider(DataGenerator generator) {
		super("Item-Tags", generator);
	}
	
	@Override
	protected void registerTags() {
		getBuilder(BACKPACK).add(UsefulBackpacksItems.SMALL_BACKPACK, UsefulBackpacksItems.MEDIUM_BACKPACK, UsefulBackpacksItems.LARGE_BACKPACK);
	}
	
}