package info.u_team.useful_backpacks.recipe;

import info.u_team.u_team_core.api.registry.IURegistryType;
import net.minecraft.item.crafting.*;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class URecipeSerializer<T extends IRecipe<?>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T>, IURegistryType {
	
	protected final String name;
	
	public URecipeSerializer(String name) {
		this.name = name;
	}
	
	@Override
	public String getEntryName() {
		return name;
	}
}
