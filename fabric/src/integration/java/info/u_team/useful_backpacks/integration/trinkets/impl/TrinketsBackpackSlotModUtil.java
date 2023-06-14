package info.u_team.useful_backpacks.integration.trinkets.impl;

import java.util.Optional;

import dev.emi.trinkets.api.TrinketsApi;
import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.integration.slot_mod.util.BackpackSlotModUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class TrinketsBackpackSlotModUtil extends BackpackSlotModUtil {
	
	@Override
	public Optional<ItemStack> find(LivingEntity livingEntity) {
		return TrinketsApi.getTrinketComponent(livingEntity).map(component -> component.getEquipped(stack -> stack.getItem() instanceof Backpack).get(0).getB());
	}
	
}
