package helio.blueprints.components;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import helio.blueprints.Action;
import helio.blueprints.DataHandler;
import helio.blueprints.DataProvider;
import helio.blueprints.UnitBuilder;
import helio.blueprints.exceptions.ExtensionNotFoundException;

/**
 * This class allows to load external components of Helio from either a local jar or from one located through a URL.
 * @author Andrea Cimmino
 *
 */
public class Components {

//	private static Map<String, DataProvider> dataProviders = new HashMap<>();
//	private static Map<String, DataHandler> dataHandlers = new HashMap<>();
//	private static Map<String, UnitBuilder> mappingProcessors = new HashMap<>();
//	private static Map<String, MappingFunctions> mappingFunctions = new HashMap<>();
//

	public static final String EXTENSION_TYPE_PROVIDER = "DataProvider";
	public static final String EXTENSION_TYPE_HANDLER = "DataHandler";
	public static final String EXTENSION_TYPE_READER = "UnitBuilder";
	public static final String EXTENSION_TYPE_FUNCTION = "MappingFunctions";
	public static final String EXTENSION_TYPE_ACTION = "Action";
	
	private static List<Component> registered = new LinkedList<>();

	private Components() {
		super();
	}

	public static DataProvider newProviderInstance(String clazz) throws ExtensionNotFoundException {
		Optional<Component> cmpOpt =  registered.parallelStream().filter(cmp -> cmp.getClazz().endsWith("."+clazz)).findFirst();
		if(cmpOpt.isPresent()) {
			Component component = cmpOpt.get();
			return buildDataProvider(component.getSource(), component.getClazz());
		}else {
			throw new ExtensionNotFoundException("Provided clazz is not a loaded component");
		}
	}
	
	public static DataHandler newHandlerInstance(String clazz) throws ExtensionNotFoundException {
		Optional<Component> cmpOpt =  registered.parallelStream().filter(cmp -> cmp.getClazz().endsWith("."+clazz)).findFirst();
		if(cmpOpt.isPresent()) {
			Component component = cmpOpt.get();
			return buildDataHandler(component.getSource(), component.getClazz());
		}else {
			throw new ExtensionNotFoundException("Provided clazz is not a loaded component");
		}
	}
	
	public static UnitBuilder newBuilderInstance(String clazz) throws ExtensionNotFoundException {
		Optional<Component> cmpOpt =  registered.parallelStream().filter(cmp -> cmp.getClazz().endsWith("."+clazz)).findFirst();
		if(cmpOpt.isPresent()) {
			Component component = cmpOpt.get();
			return buildMappingLanguage(component.getSource(), component.getClazz());
		}else {
			throw new ExtensionNotFoundException("Provided clazz is not a loaded component");
		}
	}
	
	public static Action newActionInstance(String clazz) throws ExtensionNotFoundException {
		Optional<Component> cmpOpt =  registered.parallelStream().filter(cmp -> cmp.getClazz().endsWith("."+clazz)).findFirst();
		if(cmpOpt.isPresent()) {
			Component component = cmpOpt.get();
			return buildAction(component.getSource(), component.getClazz());
		}else {
			throw new ExtensionNotFoundException("Provided clazz is not a loaded component");
		}
	}
	
	
	
	
	
	// registration methods
	public static void registerAndLoad(String source, String clazz, ComponentType type) throws ExtensionNotFoundException {
		Component cmp = new Component(source, clazz, type);
		registered.add(cmp);
		//load(cmp);
	}
	
	public static void registerAndLoad(Component cmp) throws ExtensionNotFoundException {
		registered.add(cmp);
		if(cmp.getType().equals(ComponentType.PROVIDER)) {
			buildDataProvider(cmp.source, cmp.getClazz());
		}else if(cmp.getType().equals(ComponentType.HANDLER)) {
			buildDataHandler(cmp.source, cmp.getClazz());
		}else if(cmp.getType().equals(ComponentType.BUILDER)) {
			buildMappingLanguage(cmp.source, cmp.getClazz());
		}else if(cmp.getType().equals(ComponentType.ACTION)) {
			buildAction(cmp.source, cmp.getClazz());
		}
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



//	private static MappingFunctions buildMappingFunctions(String source, String clazz) throws ExtensionNotFoundException {
//		MappingFunctions MappingFunctionsPlugins = null;
//		try {
//			ComponentsLoader<MappingFunctions> loader = new ComponentsLoader<>();
//			MappingFunctionsPlugins = loader.loadClass(source, clazz, MappingFunctions.class);
//		} catch (Exception e) {
//			throw new ExtensionNotFoundException(e.toString());
//		}
//		return MappingFunctionsPlugins;
//	}


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
	
	private static Action buildAction(String source, String clazz) throws ExtensionNotFoundException {
		Action action = null;
		try {
			ComponentsLoader<Action> loader = new ComponentsLoader<>();
			action = loader.loadClass(source, clazz, Action.class);
		} catch (Exception e) {
			throw new ExtensionNotFoundException(e.toString());
		}
		return action;
	}

}
