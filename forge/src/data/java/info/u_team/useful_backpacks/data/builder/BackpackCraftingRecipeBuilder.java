package info.u_team.useful_backpacks.data.builder;

import java.util.function.Consumer;

import info.u_team.u_team_core.util.RecipeBuilderUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class BackpackCraftingRecipeBuilder extends ShapedRecipeBuilder {
	
	public BackpackCraftingRecipeBuilder(RecipeCategory category, ItemLike item, int count) {
		super(category, item, count);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(RecipeCategory category, ItemLike item) {
		return backpackRecipe(category, item, 1);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(RecipeCategory category, ItemLike item, int count) {
		return new BackpackCraftingRecipeBuilder(category, item, count);
	}
	
	@Override
	public void save(Consumer<FinishedRecipe> consumer, ResourceLocation location) {
		super.save(recipe -> consumer.accept(RecipeBuilderUtil.getRecipeWithSerializer(recipe, UsefulBackpacksRecipeSerializers.BACKPACK)), location);
	}
}
