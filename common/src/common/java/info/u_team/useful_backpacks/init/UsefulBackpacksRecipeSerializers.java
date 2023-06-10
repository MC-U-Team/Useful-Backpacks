package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.registry.CommonRegister;
import info.u_team.u_team_core.api.registry.RegistryEntry;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class UsefulBackpacksRecipeSerializers {
	
	public static final CommonRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = CommonRegister.create(Registries.RECIPE_SERIALIZER, UsefulBackpacksReference.MODID);
	
	public static final RegistryEntry<RecipeSerializer<BackpackCraftingRecipe>> BACKPACK = RECIPE_SERIALIZERS.register("crafting_backpack", BackpackCraftingRecipe.Serializer::new);
	
	static void register() {
		RECIPE_SERIALIZERS.register();
	}
	
}
