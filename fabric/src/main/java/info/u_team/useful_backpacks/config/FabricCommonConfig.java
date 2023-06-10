package info.u_team.useful_backpacks.config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import info.u_team.u_team_core.UCoreReference;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.util.ConfigValueHolder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.Util;

public class FabricCommonConfig {
	
	private static final FabricCommonConfig INSTANCE = new FabricCommonConfig();
	
	public static FabricCommonConfig getInstance() {
		return INSTANCE;
	}
	
	private final ConfigValueHolder<Boolean> allowStackingBackpacks;
	
	private final Path path = FabricLoader.getInstance().getConfigDir().resolve(UsefulBackpacksReference.MODID + ".properties");
	private final Properties properties;
	
	private FabricCommonConfig() {
		properties = new Properties();
		
		if (Files.exists(path)) {
			load();
		}
		
		properties.computeIfAbsent("allowStackingBackpacks", unused -> "true");
		
		allowStackingBackpacks = new ConfigValueHolder<>(() -> {
			return Boolean.valueOf(properties.getProperty("allowStackingBackpacks", "true"));
		}, value -> {
			properties.put("allowStackingBackpacks", value.toString());
			Util.ioPool().submit(this::save);
		});
		
		if (!Files.exists(path)) {
			save();
		}
	}
	
	private void load() {
		try (final Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			properties.load(reader);
		} catch (final IOException ex) {
			UCoreReference.LOGGER.warn("Could not read property file '" + path.toAbsolutePath() + "'", ex);
		}
	}
	
	private void save() {
		try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			properties.store(writer, "Configuration file for Useful Backpacks mod");
		} catch (final IOException ex) {
			UCoreReference.LOGGER.warn("Could not read property file '" + path.toAbsolutePath() + "'", ex);
		}
	}
	
	public static class Impl extends CommonConfig {
		
		@Override
		public ConfigValueHolder<Boolean> allowStackingBackpacks() {
			return INSTANCE.allowStackingBackpacks;
		}
	}
}
