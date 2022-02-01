package helio.blueprints.components;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Queue;

import com.google.gson.JsonObject;

import helio.bleprints.mappings.TranslationUnit;

/**
 * These components are meant to handle pieces of data in a specific format, allowing the {@link TranslationUnit} splitting data, and accessing specific pieces in order to generate an RDF document.
 * @author Andrea Cimmino Arriaga
 */
public interface DataHandler extends Serializable{

	/**
	 * This method splits the provided data into chunks using some iterator, like a Json Path or an XPath (usually provided as part of the configuration)
	 * @param dataStream A stream containing the data to be translated into RDF, that has a specific format that this Data Handler knows how to manage
	 * @return a queue in which each element corresponds to a chunk of data
	 */
	public Queue<String> splitData(InputStream dataStream);

	/**
	 * This method must select one or more elements from a chunk of data, using a provided filter (like a Json Path or a XPat)
	 * @param filter the filtering expression
	 * @param dataChunk the piece of data from which extract the information using the filtering expression
	 * @return all the occurrences found in the piece of data using the filtering expression
	 */
	public List<String> filter(String filter, String dataChunk);

	/**
	 * This method expects to receive a {@link JsonObject} with the necessary information to setup the component.<p> Check the documentation of the implementations to know more about the different configurations required by those implementations.
	 * @param configuration a suitable configuration
	 */
	public void configure(JsonObject configuration);




}
