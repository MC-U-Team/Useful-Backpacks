package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.*;

import info.u_team.u_team_core.data.CommonLanguagesProvider;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;

public class UsefulBackpacksLanguagesProvider extends CommonLanguagesProvider {
	
	public UsefulBackpacksLanguagesProvider(DataGenerator generator, String modid) {
		super(generator, modid);
	}
	
	@Override
	public void addTranslations() {
		// English
		add(UsefulBackpacksItemGroups.GROUP, "Useful Backpacks");
		add(SMALL_BACKPACK, "Small Backpack");
		add(MEDIUM_BACKPACK, "Medium Backpack");
		add(LARGE_BACKPACK, "Large Backpack");
		add(ENDERCHEST_BACKPACK, "Ender Chest Backpack");
		
		// German
		add("de_de", UsefulBackpacksItemGroups.GROUP, "Nützliche Rucksäcke");
		add("de_de", SMALL_BACKPACK, "Kleiner Rucksack");
		add("de_de", MEDIUM_BACKPACK, "Mittlerer Rucksack");
		add("de_de", LARGE_BACKPACK, "Großer Rucksack");
		add("de_de", ENDERCHEST_BACKPACK, "Endertruhen Rucksack");
	}
	
	// TODO use uteamcore methods in next release
	@Deprecated
	protected void add(ItemGroup key, String name) {
		add(key.getTranslationKey(), name);
	}
	
	@Deprecated
	protected void add(String locale, ItemGroup key, String name) {
		add(locale, key.getTranslationKey(), name);
	}
	
}
