package info.u_team.useful_backpacks.integration.curios.init;

import org.lwjgl.glfw.GLFW;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class CuriosIntegrationKeys {
	
	public static final KeyBinding OPEN_BACKPACK = new KeyBinding("key.usefulbackpacks.curiosintegration.description", GLFW.GLFW_KEY_B, "key.usefulbackpacks.curiosintegration.category");
	
}
