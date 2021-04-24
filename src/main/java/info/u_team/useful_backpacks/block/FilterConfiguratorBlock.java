package info.u_team.useful_backpacks.block;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_backpacks.container.FilterConfiguratorContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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
