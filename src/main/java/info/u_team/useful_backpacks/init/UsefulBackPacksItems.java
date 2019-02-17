package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.registry.ItemRegistry;
import info.u_team.u_team_core.util.RegistryUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import net.minecraft.item.Item;

public class UsefulBackPacksItems {
	
	public static void construct() {
		ItemRegistry.register(UsefulBackpacksMod.modid, RegistryUtil.getRegistryEntries(Item.class, UsefulBackPacksItems.class));
	}
	
}
