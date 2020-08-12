package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class UsefulBackpacksRecipeSerializers {
	
	public static final CommonDeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = CommonDeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, UsefulBackpacksMod.MODID);
	
	public static final RegistryObject<IRecipeSerializer<BackpackCraftingRecipe>> BACKPACK = RECIPE_SERIALIZERS.register("crafting_backpack", BackpackCraftingRecipe.Serializer::new);
	
	public static void registerMod(IEventBus bus) {
		RECIPE_SERIALIZERS.register(bus);
	}
	
}
