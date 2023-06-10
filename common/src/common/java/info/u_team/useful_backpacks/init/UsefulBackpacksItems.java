package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.registry.CommonRegister;
import info.u_team.u_team_core.api.registry.RegistryEntry;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.item.BackpackItem;
import info.u_team.useful_backpacks.item.EnderChestBackpackItem;
import info.u_team.useful_backpacks.item.ItemFilterItem;
import info.u_team.useful_backpacks.item.TagFilterItem;
import info.u_team.useful_backpacks.type.BackpackType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class UsefulBackpacksItems {
	
	public static final CommonRegister<Item> ITEMS = CommonRegister.create(Registries.ITEM, UsefulBackpacksReference.MODID);
	
	public static final RegistryEntry<BackpackItem> SMALL_BACKPACK = ITEMS.register("backpack_" + BackpackType.SMALL.getName(), () -> new BackpackItem(BackpackType.SMALL));
	public static final RegistryEntry<BackpackItem> MEDIUM_BACKPACK = ITEMS.register("backpack_" + BackpackType.MEDIUM.getName(), () -> new BackpackItem(BackpackType.MEDIUM));
	public static final RegistryEntry<BackpackItem> LARGE_BACKPACK = ITEMS.register("backpack_" + BackpackType.LARGE.getName(), () -> new BackpackItem(BackpackType.LARGE));
	
	public static final RegistryEntry<EnderChestBackpackItem> ENDERCHEST_BACKPACK = ITEMS.register("backpack_enderchest", EnderChestBackpackItem::new);
	
	public static final RegistryEntry<ItemFilterItem> ITEM_FILTER = ITEMS.register("filter_item", ItemFilterItem::new);
	public static final RegistryEntry<TagFilterItem> TAG_FILTER = ITEMS.register("filter_tag", TagFilterItem::new);
	
	static void register() {
		ITEMS.register();
	}
	
}
