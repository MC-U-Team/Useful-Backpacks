package info.u_team.useful_backpacks.integration.trinkets.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import info.u_team.u_team_core.util.CastUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class TrinketsBackpackRenderer implements TrinketRenderer {
	
	@Override
	public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> model, PoseStack poseStack, MultiBufferSource bufferSource, int light, LivingEntity livingEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if (livingEntity.hasItemInSlot(EquipmentSlot.CHEST)) {
			return;
		}
		if (!(livingEntity instanceof AbstractClientPlayer player)) {
			return;
		}
		
		TrinketRenderer.translateToChest(poseStack, CastUtil.uncheckedCast(model), player);
		
		poseStack.mulPose(Axis.XP.rotation(Mth.PI));
		
		poseStack.translate(0, 0.05, -0.3);
		
		poseStack.scale(0.6F, 0.6F, 0.6F);
		
		Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, 0xF000F0, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, livingEntity.level(), 0);
	}
	
}
