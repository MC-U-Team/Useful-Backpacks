package info.u_team.useful_backpacks.integration.curios.capability;

import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.type.capability.ICurio;

public record CuriosBackpackCapability(ItemStack stack) implements ICurio {
	
	@Override
	public ItemStack getStack() {
		return stack;
	}
	
}
