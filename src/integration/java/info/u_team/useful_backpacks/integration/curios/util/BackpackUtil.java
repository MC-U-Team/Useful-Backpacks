package info.u_team.useful_backpacks.integration.curios.util;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import info.u_team.useful_backpacks.item.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

public class BackpackUtil {
	
	public static Optional<ImmutableTriple<String, Integer, ItemStack>> getBackpack(LivingEntity livingEntity) {
		return CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof BackpackItem || stack.getItem() instanceof EnderChestBackpackItem, livingEntity);
	}
	
}
