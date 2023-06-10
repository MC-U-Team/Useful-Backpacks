package info.u_team.useful_backpacks.mixin;

import org.spongepowered.asm.mixin.Mixin;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.item.BackpackItem;
import info.u_team.useful_backpacks.menu.BackpackMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Mixin(BackpackItem.class)
abstract class BackpackItemMixin extends UItem {
	
	private BackpackItemMixin(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.isSameItem(oldStack, newStack);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, Player player) {
		return !(player.containerMenu instanceof BackpackMenu);
	}
	
}
