package info.u_team.useful_backpacks.integration.curios.util;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import info.u_team.useful_backpacks.api.Backpack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

public class BackpackCuriosUtil {
	
	public static Optional<ImmutableTriple<String, Integer, ItemStack>> getBackpack(LivingEntity livingEntity) {
		return CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof Backpack, livingEntity);
	}
	
}
