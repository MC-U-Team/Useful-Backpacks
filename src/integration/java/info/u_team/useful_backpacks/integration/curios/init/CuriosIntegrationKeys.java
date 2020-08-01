package info.u_team.useful_backpacks.integration.curios.init;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.lwjgl.glfw.GLFW;

import info.u_team.u_team_core.util.registry.ClientRegistry;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.event.TickEvent.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.CuriosApi;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class CuriosIntegrationKeys {
	
	public static final KeyBinding OPEN_BACKPACK = new KeyBinding("key.usefulbackpacks.curiosintegration.description", GLFW.GLFW_KEY_V, "key.usefulbackpacks.curiosintegration.category");
	
	static {
		OPEN_BACKPACK.setKeyConflictContext(new IKeyConflictContext() {
			
			@Override
			public boolean isActive() {
				return getBackpack(Minecraft.getInstance().player).isPresent();
			}
			
			@Override
			public boolean conflicts(IKeyConflictContext other) {
				return false;
			}
		});
	}
	
	public static Optional<ImmutableTriple<String, Integer, ItemStack>> getBackpack(LivingEntity livingEntity) {
		return CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof BackpackItem || stack.getItem() instanceof EnderChestBackpackItem, livingEntity);
	}
	
	@SubscribeEvent
	public static void register(FMLClientSetupEvent event) {
		ClientRegistry.registerKeybinding(OPEN_BACKPACK);
	}
	
	@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.FORGE, value = Dist.CLIENT)
	public static class Handler {
		
		@SubscribeEvent
		public static void onKeyPress(ClientTickEvent event) {
			if (event.phase != Phase.END) {
				return;
			}
			if (Minecraft.getInstance().isGameFocused() && OPEN_BACKPACK.isKeyDown()) {
				System.out.println("PRESS | TICK");
			}
		}
	}
	
}
