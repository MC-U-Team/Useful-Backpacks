package info.u_team.useful_backpacks.recipe;

import com.google.common.collect.*;

import net.minecraft.block.*;
import net.minecraft.item.DyeColor;

public class ColorUtil {
	
	private static final BiMap<Block, DyeColor> WOOL_DYE_COLORS = HashBiMap.create();
	
	static {
		WOOL_DYE_COLORS.put(Blocks.WHITE_WOOL, DyeColor.WHITE);
		WOOL_DYE_COLORS.put(Blocks.ORANGE_WOOL, DyeColor.ORANGE);
		WOOL_DYE_COLORS.put(Blocks.MAGENTA_WOOL, DyeColor.MAGENTA);
		WOOL_DYE_COLORS.put(Blocks.LIGHT_BLUE_WOOL, DyeColor.LIGHT_BLUE);
		WOOL_DYE_COLORS.put(Blocks.YELLOW_WOOL, DyeColor.YELLOW);
		WOOL_DYE_COLORS.put(Blocks.LIME_WOOL, DyeColor.LIME);
		WOOL_DYE_COLORS.put(Blocks.PINK_WOOL, DyeColor.PINK);
		WOOL_DYE_COLORS.put(Blocks.GRAY_WOOL, DyeColor.GRAY);
		WOOL_DYE_COLORS.put(Blocks.LIGHT_GRAY_WOOL, DyeColor.LIGHT_GRAY);
		WOOL_DYE_COLORS.put(Blocks.CYAN_WOOL, DyeColor.CYAN);
		WOOL_DYE_COLORS.put(Blocks.PURPLE_WOOL, DyeColor.PURPLE);
		WOOL_DYE_COLORS.put(Blocks.BLUE_WOOL, DyeColor.BLUE);
		WOOL_DYE_COLORS.put(Blocks.BROWN_WOOL, DyeColor.BROWN);
		WOOL_DYE_COLORS.put(Blocks.GREEN_WOOL, DyeColor.GREEN);
		WOOL_DYE_COLORS.put(Blocks.RED_WOOL, DyeColor.RED);
		WOOL_DYE_COLORS.put(Blocks.BLACK_WOOL, DyeColor.BLACK);
	}
	
	public static Block getWoolFromColor(DyeColor color) {
		return WOOL_DYE_COLORS.inverse().get(color);
	}
	
	public static DyeColor getColorFromWool(Block block) {
		return WOOL_DYE_COLORS.get(block);
	}
	
}
