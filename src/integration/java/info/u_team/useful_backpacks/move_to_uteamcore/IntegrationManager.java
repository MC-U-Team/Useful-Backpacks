package info.u_team.useful_backpacks.move_to_uteamcore;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.*;
import org.objectweb.asm.Type;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData.AnnotationData;

public class IntegrationManager {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static void callIntegrations(String modid) {
		final Type type = Type.getType(IIntegration.class);
		
		final List<AnnotationData> annotations = ModList.get() //
				.getModFileById(modid) //
				.getFile() //
				.getScanResult() //
				.getAnnotations() //
				.stream() //
				.filter(data -> type.equals(data.getAnnotationType())) //
				.collect(Collectors.toList());
		
		for (AnnotationData data : annotations) {
			if (ModList.get().isLoaded((String) data.getAnnotationData().get("value"))) {
				try {
					Class.forName(data.getMemberName()).asSubclass(IModIntegration.class).newInstance().construct();
				} catch (Exception ex) {
					LOGGER.error("Failed to load and construct integration : {}", data.getMemberName(), ex);
				}
			}
		}
	}
}
