package info.u_team.useful_backpacks.data.builder;

import java.util.function.Consumer;

import info.u_team.u_team_core.util.RecipeBuilderUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksRecipeSerializers;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class BackpackCraftingRecipeBuilder extends ShapedRecipeBuilder {
	
	public BackpackCraftingRecipeBuilder(IItemProvider item, int count) {
		super(item, count);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(IItemProvider item) {
		return backpackRecipe(item, 1);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(IItemProvider item, int count) {
		return new BackpackCraftingRecipeBuilder(item, count);
	}
	
	@Override
	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation location) {
		super.build(recipe -> consumer.accept(RecipeBuilderUtil.getRecipeWithSerializer(recipe, UsefulBackpacksRecipeSerializers.BACKPACK.get())), location);
	}
}
