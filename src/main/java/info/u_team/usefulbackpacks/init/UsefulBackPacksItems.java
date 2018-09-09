package info.u_team.usefulbackpacks.init;

import info.u_team.u_team_core.registry.ItemRegistry;
import info.u_team.u_team_core.util.RegistryUtil;
import info.u_team.usefulbackpacks.UsefulBackPacksConstants;
import info.u_team.usefulbackpacks.item.ItemBackPack;
import net.minecraft.item.Item;

public class UsefulBackPacksItems {
	
	public static final ItemBackPack backpack = new ItemBackPack("backpack");
	
	public static void preinit() {
		ItemRegistry.register(UsefulBackPacksConstants.MODID, RegistryUtil.getRegistryEntries(Item.class, UsefulBackPacksItems.class));
	}
	
}
