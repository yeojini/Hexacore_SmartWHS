package server;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActiveConnection {

	static Map<String,ObjectOutputStream> ipToOos= new HashMap<String,ObjectOutputStream> ();
	static Map<String,String> idToIp= new HashMap<String,String> ();

}
