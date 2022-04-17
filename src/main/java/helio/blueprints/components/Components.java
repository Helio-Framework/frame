package helio.blueprints.components;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import helio.blueprints.DataHandler;
import helio.blueprints.DataProvider;
import helio.blueprints.MappingFunctions;
import helio.blueprints.UnitBuilder;
import helio.blueprints.exceptions.ExtensionNotFoundException;

/**
 * This class allows to load external components of Helio from either a local jar or from one located through a URL.
 * @author Andrea Cimmino
 *
 */
public class Components {

	private static Map<String, DataProvider> dataProviders = new HashMap<>();
	private static Map<String, DataHandler> dataHandlers = new HashMap<>();
	private static Map<String, UnitBuilder> mappingProcessors = new HashMap<>();
	private static Map<String, MappingFunctions> mappingFunctions = new HashMap<>();


	public static final String EXTENSION_TYPE_PROVIDER = "DataProvider";
	public static final String EXTENSION_TYPE_HANDLER = "DataHandler";
	public static final String EXTENSION_TYPE_READER = "UnitBuilder";
	public static final String EXTENSION_TYPE_FUNCTION = "MappingFunctions";
	
	private static List<Component> registered = new LinkedList<>();

	private Components() {
		super();
	}

	// registration methods
	public static void registerAndLoad(String source, String clazz, ComponentType type) throws ExtensionNotFoundException {
		Component cmp = new Component(source, clazz, type);
		registered.add(cmp);
		load(cmp);
	}
	
	public static void registerAndLoad(Component cmp) throws ExtensionNotFoundException {
		registered.add(cmp);
		load(cmp);
	}
	
	public static void register(String source, String clazz, ComponentType type) {
		registered.add(new Component(source, clazz, type));
	}
	
	public static void register(Component component) {
		registered.add(component);
	}
	
	public static List<Component> getRegistered(){
		return registered;
	}
	
	// Load Methods
	
	/**
	 * This method allows to register a component 
	 * @param className The class name to be loaded as an object
	 * @throws ExtensionNotFoundException is thrown if the name does not correspond to any registered component
	 */
	public static void load(String className) throws ExtensionNotFoundException {
		Optional<Component> cmpOpt = registered.parallelStream().filter(cmp -> cmp.getClazz().endsWith("."+className)).findFirst();
		if(!cmpOpt.isPresent())
			throw new ExtensionNotFoundException("check if the class privied "+className+" was registered as a component");
		Component cmp = cmpOpt.get();
		load(cmp, className);

	}
	public static void load(Component component) throws ExtensionNotFoundException {
		String clazz = component.getClazz();
		String name = component.getClazz().substring(clazz.lastIndexOf('.')+1);
		load(component, name);
	}
	
	protected static void load(Component component, String className) throws ExtensionNotFoundException {
		if (component.getType().equals(ComponentType.PROVIDER)) {
			DataProvider provider = buildDataProvider(component.getSource(), component.getClazz());
			dataProviders.put(className, provider); 
		}else if (component.getType().equals(ComponentType.HANDLER)) {
			DataHandler handler = buildDataHandler(component.getSource(), component.getClazz());
			dataHandlers.put(className, handler); 

		}else if (component.getType().equals(ComponentType.FUNCTION)) {
			MappingFunctions function = buildMappingFunctions(component.getSource(), component.getClazz());
			mappingFunctions.put(className, function); 

		}else if (component.getType().equals(ComponentType.BUILDER)) {
			UnitBuilder reader = buildMappingLanguage(component.getSource(), component.getClazz());
			mappingProcessors.put(className, reader); 
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
	 * Returns the {@link UnitBuilder} components
	 * @return a map with the name of the class as key and the {@link UnitBuilder} as value
	 */
	public static Map<String, UnitBuilder> getMappingProcessors() {
		return mappingProcessors;
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


	private static UnitBuilder buildMappingLanguage(String source, String clazz) throws ExtensionNotFoundException {
		UnitBuilder materialiserTranslatorPlugins = null;
		try {
			ComponentsLoader<UnitBuilder> loader = new ComponentsLoader<>();
			materialiserTranslatorPlugins = loader.loadClass(source, clazz, UnitBuilder.class);
		} catch (Exception e) {
			throw new ExtensionNotFoundException(e.toString());
		}
		return materialiserTranslatorPlugins;
	}

}
