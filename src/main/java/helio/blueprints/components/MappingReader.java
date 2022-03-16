package helio.blueprints.components;

import helio.blueprints.exceptions.ExtensionNotFoundException;
import helio.blueprints.exceptions.IncompatibleMappingException;
import helio.blueprints.exceptions.IncorrectMappingException;
import helio.blueprints.mappings.Mapping;

/**
 * These components are responsible of generating a {@link Mapping} from a specific representation, e.g., from an RML mapping.
 * @author Andrea Cimmino Arriaga
 */
public interface MappingReader {


	/**
	 * This method receives a representation of a mapping and builds an equivalent {@link Mapping}
	 * @param content a representation of a mapping
	 * @return a serialized {@link Mapping}
	 * @throws IncompatibleMappingException is thrown when the representation is not compatible with a {@link MappingReader}
	 * @throws IncorrectMappingException if the representation of a mapping has syntax errors
	 * @throws ExtensionNotFoundException if the mapping referenced a Component not registered
	 */
	public Mapping readMapping(String content) throws IncompatibleMappingException, IncorrectMappingException, ExtensionNotFoundException;


}
