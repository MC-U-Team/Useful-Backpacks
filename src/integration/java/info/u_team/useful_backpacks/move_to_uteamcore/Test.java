package info.u_team.useful_backpacks.move_to_uteamcore;

@IIntegration("curios")
public class Test implements IModIntegration {
	
	static {
		System.out.println("---------------------------------- LOADED");
	}
	
	{
		System.out.println("XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
	}
	
	public Test() {
		System.out.println("CTOR SDFJOESDZ LSDJKFHDK LHSJ");
	}
	
	@Override
	public void construct() {
		System.out.println("CALL if curios is there");
	}
	
}
