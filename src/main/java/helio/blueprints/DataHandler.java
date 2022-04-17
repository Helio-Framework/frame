package helio.blueprints;

import java.util.List;

/**
 * The {@link DataHandler} implementations are meant for filtering or handling pieces of data in a specific format, allowing the {@link TranslationUnit} splitting or filtering data, and accessing specific fragments to generate RDF.
 * @author Andrea Cimmino Arriaga
 */
public interface DataHandler extends ConfigurableBlueprint {

	/**
	 * This method filters or retrieves some fragments of data from a chunk using a filtering expressions, e.g., JSONPath or a XPat.
	 * @param filter the filtering expression
	 * @param dataChunk the piece of data from which the information using the filtering expression will be extracted
	 * @return all the occurrences found in the piece of data using the filtering expression
	 */
	public List<String> filter(String filter, String dataChunk);


	public List<String> iterator(String dataChunk);

}
