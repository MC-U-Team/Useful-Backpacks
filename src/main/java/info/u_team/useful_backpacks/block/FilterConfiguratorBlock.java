package info.u_team.useful_backpacks.block;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class FilterConfiguratorBlock extends UBlock {
	
	public FilterConfiguratorBlock() {
		super(UsefulBackpacksItemGroups.GROUP, Properties.create(Material.WOOD).harvestTool(ToolType.AXE).hardnessAndResistance(2.5F).sound(SoundType.WOOD));
	}
	
}
