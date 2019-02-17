package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.registry.ItemRegistry;
import info.u_team.u_team_core.util.RegistryUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.item.Item;

public class UsefulBackPacksItems {
	
	public static final ItemBackPack small = new ItemBackPack(EnumBackPacks.SMALL);
	public static final ItemBackPack medium = new ItemBackPack(EnumBackPacks.MEDIUM);
	public static final ItemBackPack large = new ItemBackPack(EnumBackPacks.LARGE);
	
	public static void construct() {
		ItemRegistry.register(UsefulBackpacksMod.modid, RegistryUtil.getRegistryEntries(Item.class, UsefulBackPacksItems.class));
	}
	
}
