package info.u_team.useful_backpacks.integration.jei;

import info.u_team.u_team_core.api.dye.DyeableItem;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.init.UsefulBackpacksItems;
import info.u_team.useful_backpacks.integration.jei.extension.BackpackCraftingRecipeCategoryExtension;
import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class UsefulBackpacksJeiPlugin implements IModPlugin {
	
	private final ResourceLocation id = new ResourceLocation(UsefulBackpacksReference.MODID, "jei");
	
	@Override
	public ResourceLocation getPluginUid() {
		return id;
	}
	
	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		final IIngredientSubtypeInterpreter<ItemStack> interpreter = (stack, context) -> {
			if (context == UidContext.Ingredient) {
				if (stack.hasTag() && stack.getItem() instanceof final DyeableItem item) {
					return Integer.toString(item.getColor(stack));
				}
			}
			return IIngredientSubtypeInterpreter.NONE;
		};
		
		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, UsefulBackpacksItems.SMALL_BACKPACK.get(), interpreter);
		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, UsefulBackpacksItems.MEDIUM_BACKPACK.get(), interpreter);
		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, UsefulBackpacksItems.LARGE_BACKPACK.get(), interpreter);
	}
	
	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
		registration.getCraftingCategory().addExtension(BackpackCraftingRecipe.class, new BackpackCraftingRecipeCategoryExtension());
	}
}
