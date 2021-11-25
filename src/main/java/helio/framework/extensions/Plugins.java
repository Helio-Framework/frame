package helio.framework.extensions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import helio.framework.FrameworkUtils;
import helio.framework.mappings.MappingFunctions;
import helio.framework.mappings.datasources.DataHandler;
import helio.framework.mappings.datasources.DataProvider;
import helio.framework.mappings.readers.MappingReader;

public class Plugins {

	private static Map<String,String> dataProviderPlugins = new HashMap<>();
	private static Map<String,String> dataHandlerPlugins = new HashMap<>();
	private static Map<String,String> MappingFunctionsPlugins = new HashMap<>();
	private static Map<String,String> translatorPlugins = new HashMap<>();
	private static Map<String,String> cachePlugins = new HashMap<>();
	
	private static final String JSON_TOKEN_PLUGINS = "plugins";
	private static final String JSON_TOKEN_PLUGINS_SOURCE= "source";
	private static final String JSON_TOKEN_PLUGINS_CLASS = "class";
	private static final String JSON_TOKEN_PLUGINS_CLASS_TYPE = "type";
	private static final List<String> JSON_TOKEN_PLUGINS_CLASS_TYPE_AVAILABLE = new ArrayList<>(); 
	static {
		JSON_TOKEN_PLUGINS_CLASS_TYPE_AVAILABLE.add("DataProvider");
		JSON_TOKEN_PLUGINS_CLASS_TYPE_AVAILABLE.add("DataHandler");
		JSON_TOKEN_PLUGINS_CLASS_TYPE_AVAILABLE.add("MappingFunctions");
		JSON_TOKEN_PLUGINS_CLASS_TYPE_AVAILABLE.add("MappingReader");
	}
	
	private Plugins() {
		super();
	}
	
	// Load Methods
	
	public static void loadPluginsFromJsonConfiguration(JsonObject jsonObject) {
		if(jsonObject.has(JSON_TOKEN_PLUGINS)) {
			JsonArray jsonArray = jsonObject.getAsJsonArray(JSON_TOKEN_PLUGINS);
			for(int index=0; index< jsonArray.size(); index++) {
				JsonObject pluginModule = jsonArray.get(index).getAsJsonObject();
				loadPluginsFromJsonModule(pluginModule);	
			}
		}else {
			System.out.println("[INFO] Plugins configuration was not specified in the configuration file using key 'plugins'");
		}
		
	}
	
	public static void loadPluginsFromJsonModule(JsonObject pluginModule) {
		if(pluginModule.has(JSON_TOKEN_PLUGINS_SOURCE) && pluginModule.has(JSON_TOKEN_PLUGINS_CLASS) && pluginModule.has(JSON_TOKEN_PLUGINS_CLASS_TYPE)) {
			String type = pluginModule.get(JSON_TOKEN_PLUGINS_CLASS_TYPE).getAsString();
			String source = pluginModule.get(JSON_TOKEN_PLUGINS_SOURCE).getAsString();
			String clazz = pluginModule.get(JSON_TOKEN_PLUGINS_CLASS).getAsString();
			if(!type.isEmpty() && !source.isEmpty() && !clazz.isEmpty()) {
				if(JSON_TOKEN_PLUGINS_CLASS_TYPE_AVAILABLE.contains(type)) {
					loadPluginPointer( source,  clazz,  type) ;
				}else {
					System.out.println(FrameworkUtils.concatenate("Provided plugin type is not supported, supported plugin types are:",JSON_TOKEN_PLUGINS_CLASS_TYPE_AVAILABLE.toString(),". Provided one was: ",type));
				}
			}else {
				System.out.println(FrameworkUtils.concatenate("No empty values allowed for the keys 'source', 'class', 'type'"));

			}
		}else{
			System.out.println(FrameworkUtils.concatenate("Provided json is missing mandatory keys, keys must be: 'source', 'class', 'type'. Instead provided json has ",pluginModule.keySet().toString()));
		}
	}
	
	private static void loadPluginPointer(String source, String clazz, String type) {
		if(type.equals("DataProvider"))
			dataProviderPlugins.put(clazz, source);
		if(type.equals("DataHandler"))
			dataHandlerPlugins.put(clazz, source);
		if(type.equals("MappingFunctions"))
			MappingFunctionsPlugins.put(clazz, source);
		if(type.equals("HelioCache"))
			cachePlugins.put(clazz, source);
		if(type.equals("MappingReader"))
			translatorPlugins.put(clazz, source);
	
	}
	
	// Build methods
	
	
	public static DataHandler buildDataHandlerByName(String className) {
		DataHandler dataHandlerPlugin = null;
		Optional<Entry<String,String>> entryFoundOpt = dataHandlerPlugins.entrySet().stream().filter(entry-> entry.getKey().endsWith("."+className)).findFirst();
		if(entryFoundOpt.isPresent()) {
			Entry<String,String> entryFound = entryFoundOpt.get();
			dataHandlerPlugin = buildDataHandler(entryFound.getValue(), entryFound.getKey());
		}else {
			System.out.println("Requested class is not present in the available data handler plugins, which are : "+ dataHandlerPlugins.values().toString());
		}
		return dataHandlerPlugin;
	}
	
	public static DataHandler buildDataHandler(String source, String clazz) {
		DataHandler dataHandlerPlugin = null;
		try {
			ExtensionLoader<DataHandler> loader = new ExtensionLoader<>();
			dataHandlerPlugin = loader.loadClass(source, clazz, DataHandler.class);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return dataHandlerPlugin;
	}
	
	public static DataProvider buildDataProviderByName(String className) {
		DataProvider dataProviderPlugin = null;
		Optional<Entry<String,String>> entryFoundOpt = Plugins.dataProviderPlugins.entrySet().stream().filter(entry-> entry.getKey().endsWith("."+className)).findFirst();
		if(entryFoundOpt.isPresent()) {
			Entry<String,String> entryFound = entryFoundOpt.get();
			dataProviderPlugin = buildDataProvider(entryFound.getValue(), entryFound.getKey());
		}else {
			System.out.println("Requested class is not present in the available data provider plugins, which are : "+ dataHandlerPlugins.values().toString());
		}
		return dataProviderPlugin;
	}
	
	public static DataProvider buildDataProvider(String source, String clazz) {
		DataProvider dataProviderPlugin = null;
		try {
			ExtensionLoader<DataProvider> loader = new ExtensionLoader<>();
			dataProviderPlugin = loader.loadClass(source, clazz, DataProvider.class);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return dataProviderPlugin;
	}
	
	public static Set<MappingFunctions> buildLoadedMappingFunctions(){
		Set<MappingFunctions> MappingFunctions = new HashSet<>();
		for(Entry<String,String> entry:MappingFunctionsPlugins.entrySet()){
			MappingFunctions translator = buildMappingFunctions(entry.getValue(), entry.getKey());
			MappingFunctions.add(translator);
		}
		return MappingFunctions;
	}
	
	
	public static MappingFunctions buildMappingFunctionsByName(String className) {
		MappingFunctions MappingFunctionsPlugin = null;
		Optional<Entry<String,String>> entryFoundOpt = MappingFunctionsPlugins.entrySet().stream().filter(entry-> entry.getKey().endsWith("."+className)).findFirst();
		if(entryFoundOpt.isPresent()) {
			Entry<String,String> entryFound = entryFoundOpt.get();
			MappingFunctionsPlugin = buildMappingFunctions(entryFound.getValue(), entryFound.getKey());
		}else {
			System.out.println("Requested class is not present in the available function plugins, which are : "+ dataHandlerPlugins.values().toString());
		}
		return MappingFunctionsPlugin;
	}
	
	public static MappingFunctions buildMappingFunctions(String source, String clazz) {
		MappingFunctions MappingFunctionsPlugins = null;
		try {
			ExtensionLoader<MappingFunctions> loader = new ExtensionLoader<>();
			MappingFunctionsPlugins = loader.loadClass(source, clazz, MappingFunctions.class);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return MappingFunctionsPlugins;
	}
	
	
	
	public static Set<MappingReader> buildLoadedMappingReader(){
		Set<MappingReader> translators = new HashSet<>();
		for(Entry<String,String> entry:translatorPlugins.entrySet()){
			MappingReader translator = buildTranslator(entry.getValue(), entry.getKey());
			translators.add(translator);
		}
		return translators;
	}
	
	public static MappingReader buildMappingReaderByName(String className) {
		MappingReader translatorPlugin = null; // check this MappingFunctions
		Optional<Entry<String,String>> entryFoundOpt = translatorPlugins.entrySet().stream().filter(entry-> entry.getKey().endsWith("."+className)).findFirst();
		if(entryFoundOpt.isPresent()) {
			Entry<String,String> entryFound = entryFoundOpt.get();
			translatorPlugin = buildTranslator(entryFound.getValue(), entryFound.getKey());
		}else {
			System.out.println("Requested class is not present in the available mapping translator plugins, which are : "+ dataHandlerPlugins.values().toString());
		}
		return translatorPlugin;
	}
	
	public static MappingReader buildTranslator(String source, String clazz) {
		MappingReader materialiserTranslatorPlugins = null;
		try {
			ExtensionLoader<MappingReader> loader = new ExtensionLoader<>();
			materialiserTranslatorPlugins = loader.loadClass(source, clazz, MappingReader.class);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return materialiserTranslatorPlugins;
	}
	
	
}
