package info.u_team.to_tea_core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.text.TranslationTextComponent;

public class TooltipCreator {
	
	private static final Object[] EMPTY = new Object[0];
	
	public static TranslationTextComponent getTooltip(Item item, String category, int line) {
		return getTooltip(item, category, line, EMPTY);
	}
	
	public static TranslationTextComponent getTooltip(Item item, String category, int line, Object... args) {
		if (!category.isEmpty()) {
			category += ".";
		}
		return new TranslationTextComponent(item.getTranslationKey() + ".tooltip." + category + line, args);
	}
	
	public static TranslationTextComponent getTooltip(Block block, String category, int line) {
		return getTooltip(block, category, line, EMPTY);
	}
	
	public static TranslationTextComponent getTooltip(Block block, String category, int line, Object... args) {
		if (!category.isEmpty()) {
			category += ".";
		}
		return new TranslationTextComponent(block.getTranslationKey() + ".tooltip." + category + line, args);
	}
	
	public static TranslationTextComponent getTooltip(String modid, String key, String category, int line) {
		return getTooltip(modid, key, category, line, EMPTY);
	}
	
	public static TranslationTextComponent getTooltip(String modid, String key, String category, int line, Object... args) {
		if (!category.isEmpty()) {
			category += ".";
		}
		return new TranslationTextComponent("general." + modid + "." + key + ".tooltip." + category + line, args);
	}
	
}
