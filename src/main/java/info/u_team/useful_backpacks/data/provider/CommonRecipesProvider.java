package info.u_team.useful_backpacks.data.provider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import info.u_team.u_team_core.data.CommonProvider;
import net.minecraft.advancements.criterion.*;
import net.minecraft.advancements.criterion.MinMaxBounds.IntBound;
import net.minecraft.data.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.Ingredient.TagList;
import net.minecraft.tags.Tag;
import net.minecraft.util.*;

public abstract class CommonRecipesProvider extends CommonProvider {
	
	public CommonRecipesProvider(DataGenerator generator) {
		super("Recipes", generator);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		final Consumer<IFinishedRecipe> consumer = recipe -> {
			try {
				final ResourceLocation recipeID = recipe.getID();
				write(cache, recipe.getRecipeJson(), path.resolve(recipeID.getNamespace()).resolve("recipes").resolve(recipe.getID().getPath() + ".json"));
				if (recipe.getAdvancementJson() != null) {
					write(cache, recipe.getAdvancementJson(), path.resolve(recipeID.getNamespace()).resolve("advancements").resolve("recipes").resolve(recipeID.getPath() + ".json"));
				}
			} catch (IOException ex) {
				LOGGER.error(marker, "Could not write data.", ex);
			}
		};
		
		addRecipes(consumer);
	}
	
	protected abstract void addRecipes(Consumer<IFinishedRecipe> consumer);
	
	@Override
	protected Path resolvePath(Path outputFolder) {
		return outputFolder.resolve("data");
	}
	
	protected InventoryChangeTrigger.Instance hasItem(Tag<Item> tag) {
		return hasItem(ItemPredicate.Builder.create().tag(tag).build());
	}
	
	protected InventoryChangeTrigger.Instance hasItem(IItemProvider item) {
		return hasItem(ItemPredicate.Builder.create().item(item).build());
	}
	
	protected InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicates) {
		return new InventoryChangeTrigger.Instance(IntBound.UNBOUNDED, IntBound.UNBOUNDED, IntBound.UNBOUNDED, predicates);
	}
	
	protected Ingredient getIngredientOfTag(Tag<Item> tag) {
		return Ingredient.fromItemListStream(Stream.of(new TagList(tag) {
			
			@Override
			public Collection<ItemStack> getStacks() {
				return Arrays.asList(new ItemStack(Items.ACACIA_BOAT)); // Return default value, so the ingredient will serialize our tag.
			}
		}));
	}
	
}
