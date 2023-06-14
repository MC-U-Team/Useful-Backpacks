package info.u_team.useful_backpacks.integration.slot_mod.util;

import java.util.Optional;

import info.u_team.u_team_core.util.ServiceUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public abstract class BackpackSlotModUtil {
	
	private static final BackpackSlotModUtil INSTANCE = ServiceUtil.loadOne(BackpackSlotModUtil.class);
	
	public static Optional<ItemStack> findBackpack(LivingEntity livingEntity) {
		return INSTANCE.find(livingEntity);
	}
	
	public abstract Optional<ItemStack> find(LivingEntity livingEntity);
	
}
