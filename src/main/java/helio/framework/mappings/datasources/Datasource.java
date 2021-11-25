package helio.framework.mappings.datasources;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;

import helio.framework.FrameworkUtils;
import helio.framework.mappings.readers.exceptions.IncorrectMappingException;

/**
 * This class represents a source of data, which consist in a {@link DataProvider} that retrieves data from the source and a {@link DataHandler} that filters and retrieves only the relevant information from the fetched data-<p>
 * Additionally, a {@link Datasource} can be synchronous, and its related RDF will be generated when required by a user, or asynchronous, and its related RDF will be generated periodically as specified in the refresh attribute.
 * @author Andrea Cimmino
 *
 */
public class Datasource {
	
	@JsonProperty("id")
	private String id;
	
	
	@JsonProperty("refresh")
	private Integer refresh; // in ms
	
	@JsonIgnore
	private DataHandler handler;
	
	@JsonIgnore
	private DataProvider provider;
	
	// -- Constructor
	
	public Datasource() {
		// empty
	}
	
	/**
	 * This constructor initializes a synchronous data source
	 * @param id a unique id for the data source
	 * @param dataHandler a {@link DataHandler} to manage the data
	 * @param dataProvider a {@link DataProvider} to fetch the data
	 */
	public Datasource(String id, DataHandler dataHandler, DataProvider dataProvider) {
		super();
		this.id = id;
//		this.dataHandler = dataHandler;
//		this.dataProvider = dataProvider;
		this.refresh = null;
	}
	
	/**
	 * This constructor initializes a asynchronous data source
	 * @param id a unique id for the data source
	 * @param dataHandler a {@link DataHandler} to manage the data
	 * @param dataProvider a {@link DataProvider} to fetch the data
	 * @param refresh a quantum of time in milliseconds, the data related to this data source will be generated periodically each refresh time provided and injected in the cache
	 */
	public Datasource(String id, DataHandler dataHandler, DataProvider dataProvider, Integer refresh) {
		super();
		this.id = id;
//		this.dataHandler = dataHandler;
//		this.dataProvider = dataProvider;
		this.refresh = refresh;
	}

	// Getters & Setters
	
	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	public DataHandler getDataHandler() {
		return handler;
	}

	public void setDataHandler(DataHandler dataHandler) {
		this.handler = dataHandler;
	}

	public DataProvider getDataProvider() {
		return provider;
	}

	public void setDataProvider(DataProvider dataProvider) {
		this.provider = dataProvider;
	}

	public Integer getRefresh() {
		return refresh;
	}

	public void setRefresh(Integer refresh) {
		this.refresh = refresh;
	}
	
	
    @JsonProperty("provider")
    private void unpackProvider(Map<String,Object> brand) throws IncorrectMappingException {
        if(!brand.containsKey("type")) {
        	throw new IncorrectMappingException("the JSON document for the provider must contain the mandatory key 'type' with a correct value");
        }else {
        	// Find in Datasources the correct class
        	String name = (String) brand.get("type");
        	if(Datasources.providers.containsKey(name)) {
        		DataProvider newProvider = Datasources.providers.get(name);
        		String config = FrameworkUtils.GSON.toJson(brand);
        		JsonObject convertedObject = FrameworkUtils.GSON.fromJson(config, JsonObject.class);        		
        		newProvider.configure(convertedObject);
        		this.setDataProvider(newProvider);
        	}else {
            	//throw new IncorrectMappingException("the Provider provided does not exists");
        	}
        }
        
       }
    
    @JsonProperty("handler")
    private void unpackHandler(Map<String,Object> brand) throws IncorrectMappingException {
    	if(!brand.containsKey("type")) {
        	throw new IncorrectMappingException("the JSON document for the provider must contain the mandatory key 'type' with a correct value");
        }else {
        	// Find in Handler the correct class
        	String name = (String) brand.get("type");
        	String config = FrameworkUtils.GSON.toJson(brand);
    		JsonObject convertedObject = FrameworkUtils.GSON.fromJson(config, JsonObject.class);        		
    		System.out.println(brand);
    		System.out.println(convertedObject);
        	System.out.println(Datasources.handlers.keySet());
        	if(Datasources.handlers.containsKey(name)) {
        		DataHandler newHandler = Datasources.handlers.get(name);
        		newHandler.configure(convertedObject);
        		this.setDataHandler(newHandler);
        	}else {
            	//throw new IncorrectMappingException("the Provider provided does not exists");
        	
        	}
        }
    }

	

	// -- Ancillary
	
	public String toJson() throws JsonProcessingException {
		return FrameworkUtils.JACKSON_MAPPER.writeValueAsString(this);
	}
	
	
	@Override
	public String toString() {
		return "DataSource (id=" + id + ", dataHandler=" + handler + ", dataProvider=" + provider + ", refresh="
				+ refresh + ")";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((handler == null) ? 0 : handler.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((refresh == null) ? 0 : refresh.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Datasource other = (Datasource) obj;
//		if (dataHandler == null) {
//			if (other.dataHandler != null)
//				return false;
//		} else if (!dataHandler.equals(other.dataHandler))
//			return false;
//		if (dataProvider == null) {
//			if (other.dataProvider != null)
//				return false;
//		} else if (!dataProvider.equals(other.dataProvider))
//			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (refresh == null) {
			if (other.refresh != null)
				return false;
		} else if (!refresh.equals(other.refresh))
			return false;
		return true;
	}

	


	
	
}
