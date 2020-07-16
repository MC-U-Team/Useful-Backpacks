package info.u_team.useful_backpacks.integration.jei;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.init.UsefulBackpacksItems;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class UsefulBackpacksJeiPlugin implements IModPlugin {
	
	private final ResourceLocation id = new ResourceLocation(UsefulBackpacksMod.MODID, "jei");
	
	@Override
	public ResourceLocation getPluginUid() {
		return id;
	}
	
	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		final ISubtypeInterpreter interpreter = stack -> {
			if (stack.hasTag() && stack.getItem() instanceof IDyeableItem) {
				final IDyeableItem item = (IDyeableItem) stack.getItem();
				return Integer.toString(item.getColor(stack));
			}
			return ISubtypeInterpreter.NONE;
		};
		
		registration.registerSubtypeInterpreter(UsefulBackpacksItems.SMALL_BACKPACK.get(), interpreter);
		registration.registerSubtypeInterpreter(UsefulBackpacksItems.MEDIUM_BACKPACK.get(), interpreter);
		registration.registerSubtypeInterpreter(UsefulBackpacksItems.LARGE_BACKPACK.get(), interpreter);
	}
	
}
