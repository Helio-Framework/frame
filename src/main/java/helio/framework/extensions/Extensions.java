package helio.framework.extensions;

import helio.framework.mappings.datasources.DataHandler;
import helio.framework.mappings.datasources.DataProvider;
import helio.framework.mappings.datasources.Datasources;
import helio.framework.mappings.readers.MappingReader;
import helio.framework.mappings.readers.Mappings;
import helio.framework.velocity.VelocityEvaluator;

public class Extensions {

	
	public static void registerEvaluationFunctions(String name, Class<?> functionsClass) {
		VelocityEvaluator.addFunctionClass(name, functionsClass);
	}
	
	public static void registerMappingReader(MappingReader reader) {
		Mappings.addMappingReader(reader);
	}
	
	public static void registerDataHandler(String name, DataHandler handler) {
		Datasources.addDataHandler(name, handler);
	}
	
	public static void registerDataProvider(String name, DataProvider provider) {
		Datasources.addDataProvider(name, provider);
	}
	
}
