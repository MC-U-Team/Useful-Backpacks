package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag.Named;

public class UsefulBackpacksTags {
	
	public static class Items {
		
		public static final Named<Item> BACKPACK = TagUtil.createItemTag(UsefulBackpacksMod.MODID, "backpack");
		
		public static final Named<Item> FILTER = TagUtil.createItemTag(UsefulBackpacksMod.MODID, "filter");
	}
	
}
