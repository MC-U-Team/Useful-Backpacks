package info.u_team.useful_backpacks.integration.jei;

import java.util.List;
import java.util.stream.Collectors;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.init.UsefulBackpacksItems;
import info.u_team.useful_backpacks.integration.jei.extension.BackpackCraftingRecipeCategoryExtension;
import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import mezz.jei.api.*;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.registration.*;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

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
	
	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
		registration.getCraftingCategory().addCategoryExtension(BackpackCraftingRecipe.class, BackpackCraftingRecipeCategoryExtension::new);
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		final List<ItemStack> items = ForgeRegistries.ITEMS.getValues().stream().filter(item -> item instanceof IDyeableItem || item instanceof IDyeableArmorItem).map(ItemStack::new).collect(Collectors.toList());
		registration.addIngredientInfo(items, VanillaTypes.ITEM, "This item can be colored with dyes", "YET IJGOSDGjkf jklödfgjk");
	}
}
