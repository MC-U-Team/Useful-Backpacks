package info.u_team.useful_backpacks.mixin;

import org.spongepowered.asm.mixin.Mixin;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.item.TagFilterItem;
import info.u_team.useful_backpacks.menu.TagFilterMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Mixin(TagFilterItem.class)
abstract class TagFilterItemMixin extends UItem {
	
	private TagFilterItemMixin(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, Player player) {
		return !(player.containerMenu instanceof TagFilterMenu);
	}
	
}
