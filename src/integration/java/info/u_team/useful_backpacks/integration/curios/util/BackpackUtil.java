package info.u_team.useful_backpacks.integration.curios.util;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import info.u_team.useful_backpacks.api.IBackpack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.CuriosAPI;

public class BackpackUtil {
	
	public static Optional<ImmutableTriple<String, Integer, ItemStack>> getBackpack(LivingEntity livingEntity) {
		return CuriosAPI.getCurioEquipped(stack -> stack.getItem() instanceof IBackpack, livingEntity);
	}
	
}
