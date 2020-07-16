package info.u_team.useful_backpacks.integration.jei;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import mezz.jei.api.*;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class UsefulBackpacksJeiPlugin implements IModPlugin {
	
	private final ResourceLocation id = new ResourceLocation(UsefulBackpacksMod.MODID, "jei");
	
	@Override
	public ResourceLocation getPluginUid() {
		return id;
	}
	
}
