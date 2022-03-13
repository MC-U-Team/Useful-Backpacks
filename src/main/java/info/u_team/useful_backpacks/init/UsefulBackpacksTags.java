package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class UsefulBackpacksTags {
	
	public static class Items {
		
		public static final TagKey<Item> BACKPACK = TagUtil.createItemTag(UsefulBackpacksMod.MODID, "backpack");
		
		public static final TagKey<Item> FILTER = TagUtil.createItemTag(UsefulBackpacksMod.MODID, "filter");
	}
	
}
