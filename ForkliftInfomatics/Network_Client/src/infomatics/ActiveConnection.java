package infomatics;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ActiveConnection {
	
	static Map<String,ObjectOutputStream> ipToOos= new HashMap<String,ObjectOutputStream> ();
	static Map<String,String> idToIp= new HashMap<String,String> ();

}
