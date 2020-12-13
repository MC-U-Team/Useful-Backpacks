package info.u_team.useful_backpacks.block;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_backpacks.container.FilterConfiguratorContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class FilterConfiguratorBlock extends UBlock {
	
	private static ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.usefulbackpacks.filter_configurator");
	
	public FilterConfiguratorBlock() {
		super(UsefulBackpacksItemGroups.GROUP, Properties.create(Material.WOOD).harvestTool(ToolType.AXE).hardnessAndResistance(2.5F).sound(SoundType.WOOD));
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			player.openContainer(state.getContainer(world, pos));
			return ActionResultType.CONSUME;
		}
	}
	
	@Override
	public INamedContainerProvider getContainer(BlockState state, World world, BlockPos pos) {
		return new SimpleNamedContainerProvider((id, inventory, player) -> {
			return new FilterConfiguratorContainer(id, inventory, IWorldPosCallable.of(world, pos));
		}, CONTAINER_NAME);
	}
	
}
