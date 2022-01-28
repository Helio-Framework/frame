package helio.blueprints.components;

import helio.bleprints.mappings.Mapping;
import helio.blueprints.exceptions.IncompatibleMappingException;
import helio.blueprints.exceptions.IncorrectMappingException;

/**
 * These components are responsible of generating a {@link Mapping} from a specific representation, e.g., from an RML mapping.
 * @author Andrea Cimmino Arriaga
 * @email cimmino@fi.upm.es
 */
public interface MappingReader {


	/**
	 * This method receives a representation of a mapping and builds an equivalent {@link Mapping}
	 * @param content a representation of a mapping
	 * @return a serialized {@link Mapping}
	 * @throws IncompatibleMappingException is thrown when the representation is not compatible with a {@link MappingReader}
	 * @throws IncorrectMappingException if the representation of a mapping has syntax errors
	 */
	public Mapping readMapping(String content) throws IncompatibleMappingException, IncorrectMappingException;


}
