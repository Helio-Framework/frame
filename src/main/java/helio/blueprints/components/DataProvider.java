package helio.blueprints.components;

import java.io.InputStream;
import java.io.Serializable;

import com.google.gson.JsonObject;

/**
 * This components provide pieces of data regardless their format, specifically, these components are meant to deal with the protocols and methods required to fetch the data.
 * @author Andrea Cimmino Arriaga
 * @email cimmino@fi.upm.es
 */
public interface DataProvider extends Serializable {

	/**
	 * This method fetches the data from a source and provides an {@link InputStream} for consuming it
	 * @return an {@link InputStream} for where the provided data can be read
	 */
	public InputStream getData();

	/**
	 * This method expects to receive a {@link JsonObject} with the necessary information to setup the component.<p> Check the documentation of the implementations to know more about the different configurations required by those implementations.
	 * @param configuration a suitable configuration
	 */
	public void configure(JsonObject configuration);




}
