package info.u_team.useful_backpacks.move_to_uteamcore;

import java.util.List;
import java.util.stream.Collectors;

import org.objectweb.asm.Type;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData.AnnotationData;

public class IntegrationManager {
	
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
		
		annotations.forEach(data -> {
//			data.getClassType().
		});
	}
}
