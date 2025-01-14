package info.u_team.useful_backpacks.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public class ItemFilterComponent {
	
	public static final Codec<ItemFilterComponent> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				Codec.BOOL.orElse(true).fieldOf("strict").forGetter(component -> component.strict), //
				ItemStack.OPTIONAL_CODEC.fieldOf("stack").forGetter(component -> component.stack)) //
				.apply(instance, ItemFilterComponent::new);
	});
	
	public static final StreamCodec<RegistryFriendlyByteBuf, ItemFilterComponent> STREAM_CODEC = StreamCodec.composite( //
			ByteBufCodecs.BOOL, component -> component.strict, //
			ItemStack.OPTIONAL_STREAM_CODEC, component -> component.stack, //
			ItemFilterComponent::new);
	
	public static final ItemFilterComponent EMPTY = new ItemFilterComponent(false, ItemStack.EMPTY);
	
	private final boolean strict;
	private final ItemStack stack;
	
	private ItemFilterComponent(boolean strict, ItemStack stack) {
		this.strict = strict;
		this.stack = stack;
	}
	
	public static ItemFilterComponent of(boolean strict, ItemStack stack) {
		return new ItemFilterComponent(strict, stack.copyWithCount(1));
	}
	
	public boolean isStrict() {
		return strict;
	}
	
	public boolean isPresent() {
		return !stack.isEmpty();
	}
	
	public ItemStack getStack() {
		return stack.copy();
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + Boolean.hashCode(strict);
		hash = 31 * hash + (stack == null ? 0 : ItemStack.hashItemAndComponents(stack));
		return hash;
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if ((object == null) || (getClass() != object.getClass())) {
			return false;
		}
		final ItemFilterComponent other = (ItemFilterComponent) object;
		return strict == other.strict && (stack == other.stack || stack != null && other.stack != null && stack.isEmpty() == other.stack.isEmpty() && ItemStack.isSameItemSameComponents(stack, other.stack));
	}
	
}
