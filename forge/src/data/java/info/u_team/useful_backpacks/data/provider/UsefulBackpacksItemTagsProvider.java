package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ENDERCHEST_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ITEM_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.LARGE_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.MEDIUM_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.SMALL_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.TAG_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksTags.Items.BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksTags.Items.FILTER;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.data.CommonItemTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksCommonTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class UsefulBackpacksItemTagsProvider extends CommonItemTagsProvider {
	
	public UsefulBackpacksItemTagsProvider(GenerationData generationData, CommonBlockTagsProvider blockTagsProvider) {
		super(generationData, blockTagsProvider);
	}
	
	@Override
	public void register(HolderLookup.Provider provider) {
		tag(BACKPACK).add(SMALL_BACKPACK.get(), MEDIUM_BACKPACK.get(), LARGE_BACKPACK.get(), ENDERCHEST_BACKPACK.get());
		tag(FILTER).add(ITEM_FILTER.get(), TAG_FILTER.get());
		
		tag(TagUtil.createItemTag("curios", "back")).addTag(BACKPACK);
		tag(TagUtil.createItemTag("trinkets", "chest/back")).addTag(BACKPACK);
		
		tag(UsefulBackpacksCommonTags.REDSTONE_DUST).add(Items.REDSTONE).addOptionalTag(Tags.Items.DUSTS_REDSTONE.location());
		tag(UsefulBackpacksCommonTags.DIAMOND_GEM).add(Items.DIAMOND).addOptionalTag(Tags.Items.GEMS_DIAMOND.location());
		tag(UsefulBackpacksCommonTags.IRON_INGOT).add(Items.IRON_INGOT).addOptionalTag(Tags.Items.INGOTS_IRON.location());
		tag(UsefulBackpacksCommonTags.END_STONE).add(Items.END_STONE).addOptionalTag(Tags.Items.END_STONES.location());
		tag(UsefulBackpacksCommonTags.NETHER_BRICK_INGOT).add(Items.NETHER_BRICK).addOptionalTag(Tags.Items.INGOTS_NETHER_BRICK.location());
	}
	
}