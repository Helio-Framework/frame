package helio.bleprints.mappings;

import java.util.Objects;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import helio.blueprints.components.DataHandler;
import helio.blueprints.components.DataProvider;

/**
 * This class represents a source of data, which consist in a
 * {@link DataProvider} that retrieves data from the source and a
 * {@link DataHandler} that filters and retrieves only the relevant information
 * from the fetched data.
 * <p>
 * Additionally, a {@link Datasource} can be synchronous (its related RDF will
 * be generated when required by a user), asynchronous, or scheduled (if it has
 * refresh value in that case the RDF is generated periodically as specified in
 * the refresh attribute).
 * <p>
 * The {@link Datasource} also store the provided {@link JsonObject} configuration for the {@link DataProvider} and the {@link DataHandler}.
 *
 * @author Andrea Cimmino Arriaga
 * @email cimmino@fi.upm.es
 *
 */
public class Datasource {

	@Expose
	private String id;

	private DataHandler handler;
	@Expose
	@SerializedName(value = "handler")
	private JsonObject handlerConfiguration;

	private DataProvider provider;
	@Expose
	@SerializedName(value = "provider")
	private JsonObject providerConfiguration;

	private Integer refresh;

	// -- Constructor

	public Datasource() {
		// empty
	}

	public Datasource(String id, DataHandler handler, DataProvider provider) {
		this.id = id;
		this.handler = handler;
		this.provider = provider;
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

	public void setRefresh(int refresh) {
		this.refresh = refresh;
	}

	public JsonObject getHandlerConfiguration() {

		return handlerConfiguration;
	}

	public void setHandlerConfiguration(JsonObject handlerConfiguration) {
		this.handlerConfiguration = handlerConfiguration;
	}

	public JsonObject getProviderConfiguration() {
		return providerConfiguration;
	}

	public void setProviderConfiguration(JsonObject providerConfiguration) {
		this.providerConfiguration = providerConfiguration;
	}

	// -- Ancillary

	@Override
	public int hashCode() {
		return Objects.hash(handlerConfiguration, id, providerConfiguration);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Datasource other = (Datasource) obj;
		return Objects.equals(handlerConfiguration, other.handlerConfiguration) && Objects.equals(id, other.id)
				&& Objects.equals(providerConfiguration, other.providerConfiguration);
	}

}
