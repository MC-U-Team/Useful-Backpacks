package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.registry.CommonRegister;
import info.u_team.u_team_core.api.registry.RegistryEntry;
import info.u_team.u_team_core.menutype.UMenuType;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.menu.BackpackMenu;
import info.u_team.useful_backpacks.menu.EnderChestBackpackMenu;
import info.u_team.useful_backpacks.menu.FilterConfiguratorMenu;
import info.u_team.useful_backpacks.menu.ItemFilterMenu;
import info.u_team.useful_backpacks.menu.TagFilterMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;

public class UsefulBackpacksMenuTypes {
	
	public static final CommonRegister<MenuType<?>> MENU_TYPES = CommonRegister.create(Registries.MENU, UsefulBackpacksReference.MODID);
	
	public static final RegistryEntry<MenuType<BackpackMenu>> BACKPACK = MENU_TYPES.register("backpack", () -> new UMenuType<>(BackpackMenu::createClientContainer));
	public static final RegistryEntry<MenuType<EnderChestBackpackMenu>> ENDERCHEST_BACKPACK = MENU_TYPES.register("backpack_enderchest", () -> new UMenuType<>(EnderChestBackpackMenu::createEnderChestContainer));
	
	public static final RegistryEntry<MenuType<FilterConfiguratorMenu>> FILTER_CONFIGURATOR = MENU_TYPES.register("filter_configurator", () -> new UMenuType<>((id, playerInventory) -> new FilterConfiguratorMenu(id, playerInventory)));
	
	public static final RegistryEntry<MenuType<ItemFilterMenu>> ITEM_FILTER = MENU_TYPES.register("item_filter", () -> new UMenuType<>(ItemFilterMenu::new));
	public static final RegistryEntry<MenuType<TagFilterMenu>> TAG_FILTER = MENU_TYPES.register("tag_filter", () -> new UMenuType<>(TagFilterMenu::new));
	
	static void register() {
		MENU_TYPES.register();
	}
}
