package info.u_team.useful_backpacks.integration.curios.util;

import java.util.Optional;

import info.u_team.useful_backpacks.api.Backpack;
import net.minecraft.world.entity.LivingEntity;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

public class BackpackCuriosUtil {
	
	public static Optional<SlotResult> getBackpack(LivingEntity livingEntity) {
		return CuriosApi.getCuriosHelper().findFirstCurio(livingEntity, stack -> stack.getItem() instanceof Backpack);
	}
	
}
