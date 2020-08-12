package info.u_team.useful_backpacks.integration.curios.capability;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosBackpackCapability implements ICurio {
	
	public CuriosBackpackCapability(ItemStack stack) {
	}
	
	@Override
	public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
		return true;
	}
	
	@Override
	public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	
}
