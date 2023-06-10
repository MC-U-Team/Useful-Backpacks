package info.u_team.useful_backpacks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import info.u_team.useful_backpacks.event.ItemPickupCommonEventHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(ItemEntity.class)
abstract class ItemEntityMixin extends Entity {
	
	private ItemEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}
	
	@Inject(method = "playerTouch", at = @At(value = "HEAD"), cancellable = true)
	private void usefulbackpacks$drop(Player player, CallbackInfo info) {
		final ItemEntity entity = ((ItemEntity) (Object) this);
		if (entity.hasPickUpDelay()) {
			return;
		}
		if (!(player instanceof ServerPlayer serverPlayer)) {
			return;
		}
		
		final ItemStack stackToPickup = entity.getItem();
		final Item item = stackToPickup.getItem();
		final int count = stackToPickup.getCount();
		
		final ItemStack resultStack = ItemPickupCommonEventHandler.insertInBackpacks(serverPlayer, stackToPickup);
		entity.setItem(resultStack.copy());
		
		if (resultStack.getCount() != stackToPickup.getCount()) {
			player.take(this, count);
			if (resultStack.isEmpty()) {
				discard();
				info.cancel();
			}
			player.awardStat(Stats.ITEM_PICKED_UP.get(item), count);
			player.onItemPickup(entity);
		}
	}
}
