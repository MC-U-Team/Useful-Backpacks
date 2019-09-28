package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public class UsefulBackpacksTags {
	
	public static class Items {
		
		public static final Tag<Item> BACKPACK = TagUtil.createItemTag(UsefulBackpacksMod.MODID, "backpack");
	}
	
}
