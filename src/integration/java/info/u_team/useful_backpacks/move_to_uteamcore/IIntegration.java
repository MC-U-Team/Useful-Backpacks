package info.u_team.useful_backpacks.move_to_uteamcore;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IIntegration {
	
	String baseMod();
	
	String integrationMod();
	
}
