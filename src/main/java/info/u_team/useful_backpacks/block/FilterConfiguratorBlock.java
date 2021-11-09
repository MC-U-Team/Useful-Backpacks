package info.u_team.useful_backpacks.block;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_backpacks.container.FilterConfiguratorContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksCreativeTabs;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class FilterConfiguratorBlock extends UBlock {
	
	private static Component CONTAINER_NAME = new TranslatableComponent("container.usefulbackpacks.filter_configurator");
	
	public FilterConfiguratorBlock() {
		super(UsefulBackpacksCreativeTabs.TAB, Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD));
	}
	
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (world.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			player.openMenu(state.getMenuProvider(world, pos));
			return InteractionResult.CONSUME;
		}
	}
	
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
		return new SimpleMenuProvider((id, inventory, player) -> {
			return new FilterConfiguratorContainer(id, inventory, ContainerLevelAccess.create(world, pos));
		}, CONTAINER_NAME);
	}
	
}
