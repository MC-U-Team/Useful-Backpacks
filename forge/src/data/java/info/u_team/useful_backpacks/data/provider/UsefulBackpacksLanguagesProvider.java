package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksBlocks.FILTER_CONFIGURATOR;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ENDERCHEST_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ITEM_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.LARGE_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.MEDIUM_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.SMALL_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.TAG_FILTER;

import info.u_team.u_team_core.data.CommonLanguagesProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_backpacks.init.UsefulBackpacksCreativeTabs;

public class UsefulBackpacksLanguagesProvider extends CommonLanguagesProvider {
	
	public UsefulBackpacksLanguagesProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register() {
		// English
		add(UsefulBackpacksCreativeTabs.TAB.get(), "Useful Backpacks");
		addItem(SMALL_BACKPACK, "Small Backpack");
		addItem(MEDIUM_BACKPACK, "Medium Backpack");
		addItem(LARGE_BACKPACK, "Large Backpack");
		addItem(ENDERCHEST_BACKPACK, "Ender Chest Backpack");
		addItem(ITEM_FILTER, "Auto Pickup Filter (Item)");
		addItem(TAG_FILTER, "Auto Pickup Filter (Tag)");
		addBlock(FILTER_CONFIGURATOR, "Backpack Filter Configurator");
		add("container.usefulbackpacks.filter_configurator", "Backpack Filter Configurator");
		add("container.usefulbackpacks.item_filter.strict", "Strict match: ");
		add("container.usefulbackpacks.item_filter.strict.tooltip", "If checked the item NBT must match the configured item");
		add("container.usefulbackpacks.tag_filter.search", "Search for tags");
		addItemTooltip(ITEM_FILTER, "not_configured", 0, "This filter is not configured");
		addItemTooltip(ITEM_FILTER, "not_configured", 1, "%s to configure the item filter");
		addItemTooltip(ITEM_FILTER, "configured", 0, "This filter is configured");
		addItemTooltip(ITEM_FILTER, "configured", 1, "Stack: %s");
		addItemTooltip(ITEM_FILTER, "configured", 2, "Strict match: %s");
		addItemTooltip(ITEM_FILTER, "configured", 3, "%s to remove current configuration");
		addItemTooltip(TAG_FILTER, "not_configured", 0, "This filter is not configured");
		addItemTooltip(TAG_FILTER, "not_configured", 1, "%s to configure the tag filter");
		addItemTooltip(TAG_FILTER, "configured", 0, "This filter is configured");
		addItemTooltip(TAG_FILTER, "configured", 1, "Tag: %s");
		addItemTooltip(TAG_FILTER, "configured", 2, "%s to remove current configuration");
		addTooltip("backpack", "filter", 0, "This backpack is configured with auto pickup filters");
		addTooltip("backpack", "filter_not_advanced", 0, "Enable advanced tooltips to see what filters are configured");
		addTooltip("backpack", "filter_applied", 0, "The following auto pickup filters are applied:");
		addTooltip("backpack", "filter_applied_item", 0, "- Item: %s");
		addTooltip("backpack", "filter_applied_item", 1, "(strict)");
		addTooltip("backpack", "filter_applied_tag", 0, "- Tag: %s");
		
		addTooltip("click", "right_click", 0, "Right click");
		addTooltip("click", "shift_right_click", 0, "Shift + Right click");
		
		// German
		add("de_de", UsefulBackpacksCreativeTabs.TAB.get(), "Nützliche Rucksäcke");
		addItem("de_de", SMALL_BACKPACK, "Kleiner Rucksack");
		addItem("de_de", MEDIUM_BACKPACK, "Mittlerer Rucksack");
		addItem("de_de", LARGE_BACKPACK, "Großer Rucksack");
		addItem("de_de", ENDERCHEST_BACKPACK, "Endertruhen Rucksack");
		addItem("de_de", ITEM_FILTER, "Automatischer Aufhebe Filter (Gegenstand)");
		addItem("de_de", TAG_FILTER, "Automatischer Aufhebe Filter (Aliasdaten)");
		addBlock("de_de", FILTER_CONFIGURATOR, "Rucksack Filter Konfigurator");
		add("de_de", "container.usefulbackpacks.filter_configurator", "Rucksack Filter Konfigurator");
		add("de_de", "container.usefulbackpacks.item_filter.strict", "Strenge Übereinstimmung: ");
		add("de_de", "container.usefulbackpacks.item_filter.strict.tooltip", "Wenn diese Option aktiviert ist, muss das Item NBT mit dem konfigurierten Item übereinstimmen");
		add("de_de", "container.usefulbackpacks.tag_filter.search", "Suche nach Aliasdaten (Tags)");
		addItemTooltip("de_de", ITEM_FILTER, "not_configured", 0, "Dieser Filter ist nicht konfiguriert");
		addItemTooltip("de_de", ITEM_FILTER, "not_configured", 1, "%s um den Gegenstandsfilter zu konfigurieren");
		addItemTooltip("de_de", ITEM_FILTER, "configured", 0, "Dieser Filter ist konfiguriert");
		addItemTooltip("de_de", ITEM_FILTER, "configured", 1, "Gegenstand: %s");
		addItemTooltip("de_de", ITEM_FILTER, "configured", 2, "Strenge Übereinstimmung: %s");
		addItemTooltip("de_de", ITEM_FILTER, "configured", 3, "%s um die aktuelle Konfiguration zu entfernen");
		addItemTooltip("de_de", TAG_FILTER, "not_configured", 0, "Dieser Filter ist nicht konfiguriert");
		addItemTooltip("de_de", TAG_FILTER, "not_configured", 1, "%s um den Aliasdatenfilter zu konfigurieren");
		addItemTooltip("de_de", TAG_FILTER, "configured", 0, "Dieser Filter ist konfiguriert");
		addItemTooltip("de_de", TAG_FILTER, "configured", 1, "Aliasdaten: %s");
		addItemTooltip("de_de", TAG_FILTER, "configured", 2, "%s um die aktuelle Konfiguration zu entfernen");
		addTooltip("de_de", "backpack", "filter", 0, "Dieser Rucksack ist mit automatischen Aufhebefilter konfiguriert");
		addTooltip("de_de", "backpack", "filter_not_advanced", 0, "Aktiviere die erweiterten Schnellinfos um zu sehen, welche Filter konfiguriert sind");
		addTooltip("de_de", "backpack", "filter_applied", 0, "Die folgenden automatischen Aufhebefilter werden angewendet:");
		addTooltip("de_de", "backpack", "filter_applied_item", 0, "- Gegenstand: %s");
		addTooltip("de_de", "backpack", "filter_applied_item", 1, "(streng)");
		addTooltip("de_de", "backpack", "filter_applied_tag", 0, "- Aliasdaten: %s");
		
		addTooltip("de_de", "click", "right_click", 0, "Rechtsklick");
		addTooltip("de_de", "click", "shift_right_click", 0, "Umschalttaste + Rechtsklick");
		
		// Integration
		// Curious
		
		// English
		add("key.usefulbackpacks.slotmodintegration.open_backpack.description", "Open backpack");
		add("key.usefulbackpacks.slotmodintegration.category", "Useful Backpacks : Curios / Trinkets Integration");
		
		// German
		add("de_de", "key.usefulbackpacks.slotmodintegration.open_backpack.description", "Öffne Rucksack");
		add("de_de", "key.usefulbackpacks.slotmodintegration.category", "Nützliche Rucksäcke : Curios / Trinkets Integration");
	}
	
}
