package info.u_team.useful_backpacks.data.provider;

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
		
		// German
		add("de_de", UsefulBackpacksItemGroups.GROUP, "Nützliche Rucksäcke");
		addItem("de_de", SMALL_BACKPACK, "Kleiner Rucksack");
		addItem("de_de", MEDIUM_BACKPACK, "Mittlerer Rucksack");
		addItem("de_de", LARGE_BACKPACK, "Großer Rucksack");
		addItem("de_de", ENDERCHEST_BACKPACK, "Endertruhen Rucksack");
		
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
