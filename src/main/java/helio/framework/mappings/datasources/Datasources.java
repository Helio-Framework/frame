package helio.framework.mappings.datasources;

import java.util.HashMap;
import java.util.Map;

public class Datasources {

	
	protected static Map<String, DataProvider> providers = new HashMap<>();
	protected static Map<String, DataHandler> handlers = new HashMap<>();
	
	public static void addDataProvider(String name, DataProvider provider) {
		providers.put(name, provider);
	}
	
	public static void addDataHandler(String name,  DataHandler handler) {
		handlers.put(name, handler);
	}
	
	
}
