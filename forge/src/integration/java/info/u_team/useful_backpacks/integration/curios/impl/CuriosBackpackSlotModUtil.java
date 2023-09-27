package info.u_team.useful_backpacks.integration.curios.impl;

import java.util.Optional;

import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.integration.slot_mod.util.BackpackSlotModUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

public class CuriosBackpackSlotModUtil extends BackpackSlotModUtil {
	
	public Optional<ItemStack> find(LivingEntity livingEntity) {
		return CuriosApi.getCuriosInventory(livingEntity).map(handler -> handler.findFirstCurio(stack -> stack.getItem() instanceof Backpack).map(SlotResult::stack)).orElse(Optional.empty());
	}
	
}
