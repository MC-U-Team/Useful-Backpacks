package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksBlocks.FILTER_CONFIGURATOR;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.*;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;

public class UsefulBackpacksLanguagesProvider extends CommonLanguagesProvider {
	
	public UsefulBackpacksLanguagesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void addTranslations() {
		// English
		add(UsefulBackpacksItemGroups.GROUP, "Useful Backpacks");
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
		addItemTooltip(ITEM_FILTER, 0, "This filter is not configured");
		addItemTooltip(ITEM_FILTER, 1, "%s to configure the item filter");
		addItemTooltip(TAG_FILTER, 0, "This filter is not configured");
		addItemTooltip(TAG_FILTER, 1, "%s to configure the tag filter");
		
		add("usefulbackpacks.tooltip.right_click", "Right click");
		
		// German
		add("de_de", UsefulBackpacksItemGroups.GROUP, "Nützliche Rucksäcke");
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
		addItemTooltip("de_de", ITEM_FILTER, 0, "Dieser Filter ist nicht konfiguriert");
		addItemTooltip("de_de", ITEM_FILTER, 1, "%s um den Gegenstandsfilter zu konfigurieren");
		addItemTooltip("de_de", TAG_FILTER, 0, "Dieser Filter ist nicht konfiguriert");
		addItemTooltip("de_de", TAG_FILTER, 1, "%s um den Aliasdatenfilter zu konfigurieren");
		
		add("de_de", "usefulbackpacks.tooltip.right_click", "Rechtsklick");
		
		// Integration
		// Curious
		
		// English
		add("key.usefulbackpacks.curiosintegration.description", "Open backpack");
		add("key.usefulbackpacks.curiosintegration.category", "Useful Backpacks : Curios Integration");
		
		// German
		add("de_de", "key.usefulbackpacks.curiosintegration.description", "Öffne Rucksack");
		add("de_de", "key.usefulbackpacks.curiosintegration.category", "Nützliche Rucksäcke : Curios Integration");
	}
	
}
