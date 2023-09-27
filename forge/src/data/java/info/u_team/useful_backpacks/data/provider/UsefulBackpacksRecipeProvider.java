package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksBlocks.FILTER_CONFIGURATOR;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ENDERCHEST_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ITEM_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.LARGE_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.MEDIUM_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.SMALL_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.TAG_FILTER;

import info.u_team.u_team_core.data.CommonRecipeProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_backpacks.data.builder.BackpackCraftingRecipeBuilder;
import info.u_team.useful_backpacks.init.UsefulBackpacksCommonTags;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

public class UsefulBackpacksRecipeProvider extends CommonRecipeProvider {
	
	public UsefulBackpacksRecipeProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void register(RecipeOutput output) {
		BackpackCraftingRecipeBuilder.backpackRecipe(RecipeCategory.TOOLS, SMALL_BACKPACK.get()) //
				.pattern("WLW") //
				.pattern("LSL") //
				.pattern("WLW") //
				.define('W', ItemTags.WOOL) //
				.define('L', Items.LEATHER) //
				.define('S', Items.STRING) //
				.unlockedBy("has_wool", has(ItemTags.WOOL)) //
				.unlockedBy("has_leather", has(Items.LEATHER)) //
				.save(output);
		
		BackpackCraftingRecipeBuilder.backpackRecipe(RecipeCategory.TOOLS, MEDIUM_BACKPACK.get()) //
				.pattern("WLW") //
				.pattern("LBL") //
				.pattern("WLW") //
				.define('W', ItemTags.WOOL) //
				.define('L', Items.LEATHER) //
				.define('B', SMALL_BACKPACK.get()) //
				.unlockedBy("has_small_backpack", has(SMALL_BACKPACK.get())) //
				.save(output);
		
		BackpackCraftingRecipeBuilder.backpackRecipe(RecipeCategory.TOOLS, LARGE_BACKPACK.get()) //
				.pattern("WLW") //
				.pattern("LBL") //
				.pattern("WLW") //
				.define('W', ItemTags.WOOL) //
				.define('L', Items.LEATHER) //
				.define('B', MEDIUM_BACKPACK.get()) //
				.unlockedBy("has_medium_backpack", has(MEDIUM_BACKPACK.get())) //
				.save(output);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ENDERCHEST_BACKPACK.get()) //
				.pattern("WLW") //
				.pattern("ESE") //
				.pattern("WLW") //
				.define('W', ItemTags.WOOL) //
				.define('L', Items.LEATHER) //
				.define('E', Items.ENDER_CHEST) //
				.define('S', Items.STRING) //
				.unlockedBy("has_enderchest", has(Items.ENDER_CHEST)) //
				.save(output);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FILTER_CONFIGURATOR.get()) //
				.pattern("WLW") //
				.pattern("PCP") //
				.pattern("WLW") //
				.define('W', ItemTags.WOOL) //
				.define('L', Items.LEATHER) //
				.define('P', ItemTags.PLANKS) //
				.define('C', Items.CRAFTING_TABLE) //
				.unlockedBy("has_wool", has(ItemTags.WOOL)) //
				.unlockedBy("has_leather", has(Items.LEATHER)) //
				.save(output);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ITEM_FILTER.get()) //
				.pattern("WLW") //
				.pattern("RDS") //
				.pattern("IEI") //
				.define('W', ItemTags.WOOL) //
				.define('L', Items.LEATHER) //
				.define('R', UsefulBackpacksCommonTags.REDSTONE_DUST) //
				.define('D', UsefulBackpacksCommonTags.DIAMOND_GEM) //
				.define('S', Items.STRING) //
				.define('I', UsefulBackpacksCommonTags.IRON_INGOT) //
				.define('E', UsefulBackpacksCommonTags.END_STONE) //
				.unlockedBy("has_small_backpack", has(SMALL_BACKPACK.get())) //
				.save(output);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TAG_FILTER.get()) //
				.pattern("WLW") //
				.pattern("RAS") //
				.pattern("IEI") //
				.define('W', ItemTags.WOOL) //
				.define('L', Items.LEATHER) //
				.define('R', UsefulBackpacksCommonTags.REDSTONE_DUST) //
				.define('A', UsefulBackpacksCommonTags.NETHER_BRICK_INGOT) //
				.define('S', Items.STRING) //
				.define('I', UsefulBackpacksCommonTags.IRON_INGOT) //
				.define('E', UsefulBackpacksCommonTags.END_STONE) //
				.unlockedBy("has_small_backpack", has(SMALL_BACKPACK.get())) //
				.save(output);
	}
}
