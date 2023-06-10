package info.u_team.useful_backpacks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.authlib.GameProfile;

import info.u_team.useful_backpacks.item.BackpackItem;
import info.u_team.useful_backpacks.item.ItemFilterItem;
import info.u_team.useful_backpacks.menu.BackpackMenu;
import info.u_team.useful_backpacks.menu.ItemFilterMenu;
import info.u_team.useful_backpacks.menu.TagFilterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(ServerPlayer.class)
abstract class ServerPlayerMixin extends Player {
	
	private ServerPlayerMixin(Level level, BlockPos pos, float yaw, GameProfile profile) {
		super(level, pos, yaw, profile);
	}
	
	@Inject(method = "drop(Z)Z", at = @At(value = "HEAD"), cancellable = true)
	private void usefulbackpacks$drop(boolean dropStack, CallbackInfoReturnable<Boolean> info) {
		final ItemStack selected = getInventory().getSelected();
		if (selected.isEmpty() //
				|| (selected.getItem() instanceof BackpackItem && containerMenu instanceof BackpackMenu) //
				|| (selected.getItem() instanceof ItemFilterItem && containerMenu instanceof ItemFilterMenu) //
				|| (selected.getItem() instanceof ItemFilterItem && containerMenu instanceof TagFilterMenu)) {
			info.setReturnValue(false);
		}
	}
	
}
