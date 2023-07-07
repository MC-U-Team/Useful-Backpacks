package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class UsefulBackpacksCommonTags {
	
	public static final TagKey<Item> REDSTONE_DUST = TagUtil.createItemTag(UsefulBackpacksReference.MODID, "dust/redstone");
	
	public static final TagKey<Item> DIAMOND_GEM = TagUtil.createItemTag(UsefulBackpacksReference.MODID, "gem/diamond");
	
	public static final TagKey<Item> IRON_INGOT = TagUtil.createItemTag(UsefulBackpacksReference.MODID, "ingot/iron");
	
	public static final TagKey<Item> END_STONE = TagUtil.createItemTag(UsefulBackpacksReference.MODID, "end_stone");
	
	public static final TagKey<Item> NETHER_BRICK_INGOT = TagUtil.createItemTag(UsefulBackpacksReference.MODID, "ingot/nether_brick");
	
}
