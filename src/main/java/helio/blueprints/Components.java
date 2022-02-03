package helio.blueprints;

import java.util.HashMap;
import java.util.Map;

import helio.blueprints.components.DataHandler;
import helio.blueprints.components.DataProvider;
import helio.blueprints.components.MappingFunctions;
import helio.blueprints.components.MappingReader;
import helio.blueprints.exceptions.ExtensionNotFoundException;

/**
 * This class allows to load external components of Helio from either a local jar or from one located through a URL.
 * @author Andrea Cimmino
 *
 */
public class Components {

	private static Map<String, DataProvider> dataProviders = new HashMap<>();
	private static Map<String, DataHandler> dataHandlers = new HashMap<>();
	private static Map<String, MappingReader> mappingReaders = new HashMap<>();
	private static Map<String, MappingFunctions> mappingFunctions = new HashMap<>();


	public static final String EXTENSION_TYPE_PROVIDER = "DataProvider";
	public static final String EXTENSION_TYPE_HANDLER = "DataHandler";
	public static final String EXTENSION_TYPE_READER = "MappingReader";
	public static final String EXTENSION_TYPE_FUNCTION = "MappingFunctions";




	private Components() {
		super();
	}


	// Load Methods
	/**
	 * This method allows to register a component
	 * @param source the path to the jar, it can be a URL or a local directory
	 * @param clazz the full class specification, e.g., components.external.handlers.JsonHandler
	 * @param type one of the types available in the {@link Components} class: Components.EXTENSION_TYPE_PROVIDER, Components.EXTENSION_TYPE_HANDLER, Components.EXTENSION_TYPE_READER, Components.EXTENSION_TYPE_FUNCTION
	 * @throws ExtensionNotFoundException is thrown when the component was not found
	 */
	public static void registerComponent(String source, String clazz, String type) throws ExtensionNotFoundException {
		String name = clazz.substring(clazz.lastIndexOf('.')+1);

		if (type.equals(EXTENSION_TYPE_PROVIDER)) {
			DataProvider provider = buildDataProvider(source, clazz);
			dataProviders.put(name, provider); //TODO: if name exists throw exception it could happen that two jars have a class with the same name
		}else if (type.equals(EXTENSION_TYPE_HANDLER)) {
			DataHandler handler = buildDataHandler(source, clazz);
			dataHandlers.put(name, handler); //TODO:  if name exists throw exception it could happen that two jars have a class with the same name

		}else if (type.equals(EXTENSION_TYPE_FUNCTION)) {
			MappingFunctions function = buildMappingFunctions(source, clazz);
			mappingFunctions.put(name, function); //TODO:  if name exists throw exception it could happen that two jars have a class with the same name

		}else if (type.equals(EXTENSION_TYPE_READER)) {
			MappingReader reader = buildMappingReader(source, clazz);
			mappingReaders.put(name, reader);  //TODO:  if name exists throw exception it could happen that two jars have a class with the same name

		}else {
			//TODO: THROW AN EXCEPTION
		}

	}

	/**
	 * Returns the {@link DataProvider} components
	 * @return a map with the name of the class as key and the {@link DataProvider} as value
	 */
	public static Map<String, DataProvider> getDataProviders() {
		return dataProviders;
	}

	/**
	 * Returns the {@link DataHandler} components
	 * @return a map with the name of the class as key and the {@link DataHandler} as value
	 */
	public static Map<String, DataHandler> getDataHandlers() {
		return dataHandlers;
	}

	/**
	 * Returns the {@link MappingReader} components
	 * @return a map with the name of the class as key and the {@link MappingReader} as value
	 */
	public static Map<String, MappingReader> getMappingReaders() {
		return mappingReaders;
	}

	/**
	 * Returns the {@link MappingFunctions} components
	 * @return a map with the name of the class as key and the {@link MappingFunctions} as value
	 */
	public static Map<String, MappingFunctions> getMappingFunctions() {
		return mappingFunctions;
	}

	// Building methods
	private static DataProvider buildDataProvider(String source, String clazz) throws ExtensionNotFoundException {
		DataProvider dataProviderPlugin = null;
		try {
			ComponentsLoader<DataProvider> loader = new ComponentsLoader<>();
			dataProviderPlugin = loader.loadClass(source, clazz, DataProvider.class);
		} catch (Exception e) {
			throw new ExtensionNotFoundException(e.toString());
		}
		return dataProviderPlugin;
	}



	private static DataHandler buildDataHandler(String source, String clazz) throws ExtensionNotFoundException {
		DataHandler dataHandlerPlugin = null;
		try {
			ComponentsLoader<DataHandler> loader = new ComponentsLoader<>();
			dataHandlerPlugin = loader.loadClass(source, clazz, DataHandler.class);
		} catch (Exception e) {
			throw new ExtensionNotFoundException(e.toString());
		}
		return dataHandlerPlugin;
	}



	private static MappingFunctions buildMappingFunctions(String source, String clazz) throws ExtensionNotFoundException {
		MappingFunctions MappingFunctionsPlugins = null;
		try {
			ComponentsLoader<MappingFunctions> loader = new ComponentsLoader<>();
			MappingFunctionsPlugins = loader.loadClass(source, clazz, MappingFunctions.class);
		} catch (Exception e) {
			throw new ExtensionNotFoundException(e.toString());
		}
		return MappingFunctionsPlugins;
	}


	private static MappingReader buildMappingReader(String source, String clazz) throws ExtensionNotFoundException {
		MappingReader materialiserTranslatorPlugins = null;
		try {
			ComponentsLoader<MappingReader> loader = new ComponentsLoader<>();
			materialiserTranslatorPlugins = loader.loadClass(source, clazz, MappingReader.class);
		} catch (Exception e) {
			throw new ExtensionNotFoundException(e.toString());
		}
		return materialiserTranslatorPlugins;
	}

}
