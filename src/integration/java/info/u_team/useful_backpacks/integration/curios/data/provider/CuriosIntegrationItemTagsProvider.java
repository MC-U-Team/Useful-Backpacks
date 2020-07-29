package info.u_team.useful_backpacks.integration.curios.data.provider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksTags;
import net.minecraft.data.DirectoryCache;
import net.minecraft.tags.*;

public class CuriosIntegrationItemTagsProvider extends CommonItemTagsProvider {
	
	public CuriosIntegrationItemTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		getBuilder(TagUtil.createItemTag("curios", "back")).addTag(UsefulBackpacksTags.Items.BACKPACK);
	}
	
	@Override
	public void act(DirectoryCache cache) {
		tagToBuilder.clear();
		registerTags();
		
		tagToBuilder.forEach((location, builder) -> {
			final List<ITag.Proxy> list = builder.func_232963_b_(id -> Tag.func_241284_a_(), id -> registry.getValue(id).orElse(null)).collect(Collectors.toList());
			if (!list.isEmpty()) {
				throw new IllegalArgumentException(String.format("Couldn't define tag %s as it is missing following references: %s", location, list.stream().map(Objects::toString).collect(Collectors.joining(","))));
			}
			final JsonObject object = builder.serialize();
			final Path path = makePath(location);
			try {
				write(cache, object, path);
			} catch (final IOException ex) {
				LOGGER.error(marker, "Could not write data.", ex);
			}
		});
	}
	
}
