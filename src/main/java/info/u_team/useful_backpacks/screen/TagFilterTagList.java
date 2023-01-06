package info.u_team.useful_backpacks.screen;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import info.u_team.u_team_core.gui.elements.ScrollableList;
import info.u_team.useful_backpacks.menu.TagFilterMenu;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.registries.ForgeRegistries;

public class TagFilterTagList extends ScrollableList<TagFilterTagListEntry> {
	
	private final TagFilterMenu menu;
	
	public TagFilterTagList(TagFilterMenu menu, int x, int y, int width, int height, String tag) {
		super(x, y, width, height, 10, 15);
		this.menu = menu;
		
		setRenderTopAndBottom(false);
		setUseScissor(true);
		setRenderTransparentBorder(true);
		
		updateEntries(unused -> true);
		
		if (!tag.isEmpty()) {
			children().stream().filter(entry -> entry.getTag().toString().equals(tag)).findAny().ifPresent(entry -> {
				super.setSelected(entry);
				centerScrollOn(entry);
			});
		}
	}
	
	@Override
	public void setSelected(TagFilterTagListEntry entry) {
		super.setSelected(entry);
		
		final String tag = entry.getTag().toString();
		
		menu.getTagMessage().triggerMessage(() -> new FriendlyByteBuf(Unpooled.buffer(100)).writeUtf(tag));
		menu.setTag(tag);
	}
	
	public void updateSearch(String search) {
		if (search.isEmpty()) {
			updateEntries(tag -> true);
		} else {
			updateEntries(tag -> {
				if (tag.getNamespace().startsWith(search) || tag.getPath().startsWith(search)) {
					return true;
				}
				return false;
			});
		}
	}
	
	private void updateEntries(Predicate<ResourceLocation> predicate) {
		final List<ResourceLocation> list = ForgeRegistries.ITEMS.tags().getTagNames().filter(tagKey -> predicate.test(tagKey.location())).map(TagKey::location).collect(Collectors.toList());
		
		Collections.sort(list, (a, b) -> {
			return a.toString().compareTo(b.toString());
		});
		final TagFilterTagListEntry selected = getSelected();
		clearEntries();
		setScrollAmount(0);
		list.forEach(tag -> {
			final TagFilterTagListEntry entry = new TagFilterTagListEntry(tag);
			addEntry(entry);
			
			if (selected != null && selected.getTag().equals(tag)) {
				super.setSelected(entry);
			}
		});
		
		if (getSelected() != null) {
			centerScrollOn(getSelected());
		}
	}
	
}
