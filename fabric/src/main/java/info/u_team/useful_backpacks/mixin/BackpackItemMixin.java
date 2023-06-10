package info.u_team.useful_backpacks.mixin;

import org.spongepowered.asm.mixin.Mixin;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Mixin(BackpackItem.class)
abstract class BackpackItemMixin extends UItem {
	
	private BackpackItemMixin(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean allowNbtUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
		return !ItemStack.isSameItem(oldStack, newStack);
	}
	
}
