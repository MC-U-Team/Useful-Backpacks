package info.u_team.useful_backpacks.recipe;

import java.util.function.Function;

import info.u_team.u_team_core.api.registry.IURegistryType;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;

public class USpecialRecipeSerializer<T extends IRecipe<?>> extends SpecialRecipeSerializer<T> implements IURegistryType {
	
	protected final String name;
	
	public USpecialRecipeSerializer(String name, Function<ResourceLocation, T> function) {
		super(function);
		this.name = name;
	}
	
	@Override
	public String getEntryName() {
		return name;
	}
	
}
