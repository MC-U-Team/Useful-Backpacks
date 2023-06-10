package info.u_team.useful_backpacks.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public record ConfigValueHolder<V> (Supplier<V> getter, Consumer<V> setter) {
	
	public V get() {
		return getter.get();
	}
	
	public void set(V value) {
		setter.accept(value);
	}
}
