package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksBlocks.FILTER_CONFIGURATOR;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ENDERCHEST_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ITEM_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.LARGE_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.MEDIUM_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.SMALL_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.TAG_FILTER;

import java.util.function.Consumer;

import info.u_team.u_team_core.data.CommonRecipeProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_backpacks.data.builder.BackpackCraftingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class UsefulBackpacksRecipeProvider extends CommonRecipeProvider {
	
	public UsefulBackpacksRecipeProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void register(Consumer<FinishedRecipe> consumer) {
		BackpackCraftingRecipeBuilder.backpackRecipe(RecipeCategory.TOOLS, SMALL_BACKPACK.get()) //
				.pattern("WLW") //
				.pattern("LSL") //
				.pattern("WLW") //
				.define('W', getIngredientOfTag(ItemTags.WOOL)) //
				.define('L', Items.LEATHER) //
				.define('S', Items.STRING) //
				.unlockedBy("has_wool", has(ItemTags.WOOL)) //
				.unlockedBy("has_leather", has(Items.LEATHER)) //
				.save(consumer);
		
		BackpackCraftingRecipeBuilder.backpackRecipe(RecipeCategory.TOOLS, MEDIUM_BACKPACK.get()) //
				.pattern("WLW") //
				.pattern("LBL") //
				.pattern("WLW") //
				.define('W', getIngredientOfTag(ItemTags.WOOL)) //
				.define('L', Items.LEATHER) //
				.define('B', SMALL_BACKPACK.get()) //
				.unlockedBy("has_small_backpack", has(SMALL_BACKPACK.get())) //
				.save(consumer);
		
		BackpackCraftingRecipeBuilder.backpackRecipe(RecipeCategory.TOOLS, LARGE_BACKPACK.get()) //
				.pattern("WLW") //
				.pattern("LBL") //
				.pattern("WLW") //
				.define('W', getIngredientOfTag(ItemTags.WOOL)) //
				.define('L', Items.LEATHER) //
				.define('B', MEDIUM_BACKPACK.get()) //
				.unlockedBy("has_medium_backpack", has(MEDIUM_BACKPACK.get())) //
				.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ENDERCHEST_BACKPACK.get()) //
				.pattern("WLW") //
				.pattern("ESE") //
				.pattern("WLW") //
				.define('W', getIngredientOfTag(ItemTags.WOOL)) //
				.define('L', Items.LEATHER) //
				.define('E', Items.ENDER_CHEST) //
				.define('S', Items.STRING) //
				.unlockedBy("has_enderchest", has(Items.ENDER_CHEST)) //
				.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FILTER_CONFIGURATOR.get()) //
				.pattern("WLW") //
				.pattern("PCP") //
				.pattern("WLW") //
				.define('W', getIngredientOfTag(ItemTags.WOOL)) //
				.define('L', Items.LEATHER) //
				.define('P', getIngredientOfTag(ItemTags.PLANKS)) //
				.define('C', Items.CRAFTING_TABLE) //
				.unlockedBy("has_wool", has(ItemTags.WOOL)) //
				.unlockedBy("has_leather", has(Items.LEATHER)) //
				.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ITEM_FILTER.get()) //
				.pattern("WLW") //
				.pattern("RDS") //
				.pattern("IEI") //
				.define('W', getIngredientOfTag(ItemTags.WOOL)) //
				.define('L', Items.LEATHER) //
				.define('R', getIngredientOfTag(Tags.Items.DUSTS_REDSTONE)) //
				.define('D', getIngredientOfTag(Tags.Items.GEMS_DIAMOND)) //
				.define('S', Items.STRING) //
				.define('I', getIngredientOfTag(Tags.Items.INGOTS_IRON)) //
				.define('E', getIngredientOfTag(Tags.Items.END_STONES)) //
				.unlockedBy("has_small_backpack", has(SMALL_BACKPACK.get())) //
				.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TAG_FILTER.get()) //
				.pattern("WLW") //
				.pattern("RAS") //
				.pattern("IEI") //
				.define('W', getIngredientOfTag(ItemTags.WOOL)) //
				.define('L', Items.LEATHER) //
				.define('R', getIngredientOfTag(Tags.Items.DUSTS_REDSTONE)) //
				.define('A', getIngredientOfTag(Tags.Items.INGOTS_NETHER_BRICK)) //
				.define('S', Items.STRING) //
				.define('I', getIngredientOfTag(Tags.Items.INGOTS_IRON)) //
				.define('E', getIngredientOfTag(Tags.Items.END_STONES)) //
				.unlockedBy("has_small_backpack", has(SMALL_BACKPACK.get())) //
				.save(consumer);
	}
}
