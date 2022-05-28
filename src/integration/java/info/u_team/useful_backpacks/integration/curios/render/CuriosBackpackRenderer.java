package info.u_team.useful_backpacks.integration.curios.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CuriosBackpackRenderer implements ICurioRenderer {
	
	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		final LivingEntity livingEntity = slotContext.entity();
		
		if (livingEntity.hasItemInSlot(EquipmentSlot.CHEST)) {
			return;
		}
		
		ICurioRenderer.translateIfSneaking(poseStack, livingEntity);
		ICurioRenderer.rotateIfSneaking(poseStack, livingEntity);
		
		poseStack.mulPose(Vector3f.XN.rotation((float) Math.PI));
		
		poseStack.translate(0, -0.3, -0.14);
		
		poseStack.scale(0.6F, 0.6F, 0.6F);
		
		Minecraft.getInstance().getItemRenderer().renderStatic(stack, TransformType.FIXED, 0xF000F0, OverlayTexture.NO_OVERLAY, poseStack, renderTypeBuffer, 0 /* TODO what does this number means? **/);
	}
	
}
