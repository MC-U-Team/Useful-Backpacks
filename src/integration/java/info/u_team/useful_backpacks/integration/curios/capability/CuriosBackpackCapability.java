package info.u_team.useful_backpacks.integration.curios.capability;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.capability.ICurio;

public class CuriosBackpackCapability implements ICurio {
	
	private final ItemStack stack;
	
	public CuriosBackpackCapability(ItemStack stack) {
		this.stack = stack;
	}
	
	@Override
	public boolean hasRender(String identifier, LivingEntity livingEntity) {
		return true;
	}
	
	@Override
	public void render(String identifier, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (livingEntity.hasItemInSlot(EquipmentSlotType.CHEST)) {
			return;
		}
		
		matrixStack.push();
		
		RenderHelper.translateIfSneaking(matrixStack, livingEntity);
		RenderHelper.rotateIfSneaking(matrixStack, livingEntity);
		
		matrixStack.rotate(Vector3f.XN.rotation((float) Math.PI));
		
		matrixStack.translate(0, -0.3, -0.14);
		
		matrixStack.scale(0.6F, 0.6F, 0.6F);
		
		Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, 0xF000F0, OverlayTexture.NO_OVERLAY, matrixStack, renderTypeBuffer);
		
		matrixStack.pop();
	}
	
}
