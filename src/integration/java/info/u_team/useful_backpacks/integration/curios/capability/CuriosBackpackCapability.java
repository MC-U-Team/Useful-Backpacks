package info.u_team.useful_backpacks.integration.curios.capability;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosBackpackCapability implements ICurio {
	
	private final ItemStack stack;
	
	public CuriosBackpackCapability(ItemStack stack) {
		this.stack = stack;
	}
	
	@Override
	public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
		return true;
	}
	
	@Override
	public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		matrixStack.push();
		
		RenderHelper.translateIfSneaking(matrixStack, livingEntity);
		RenderHelper.rotateIfSneaking(matrixStack, livingEntity);
		
		Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, 0xF000F0, OverlayTexture.NO_OVERLAY, matrixStack, renderTypeBuffer);
		
		matrixStack.pop();
	}
	
}
