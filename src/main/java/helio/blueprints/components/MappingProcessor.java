package helio.blueprints.components;

import java.util.Set;

import helio.blueprints.exceptions.ExtensionNotFoundException;
import helio.blueprints.exceptions.IncompatibleMappingException;
import helio.blueprints.exceptions.IncorrectMappingException;
import helio.blueprints.exceptions.MappingExecutionException;
import helio.blueprints.mappings.TripleMapping;

public interface MappingProcessor {

	/**
	 * This method receives a representation of a mapping and returns a VelocityTemplate
	 * @param content a representation of a mapping
	 * @return a serialized {@link MappingProcessor}
	 * @throws IncompatibleMappingException is thrown when the representation is not compatible with a {@link MappingProcessor}
	 * @throws IncorrectMappingException if the representation of a mapping has syntax errors
	 * @throws ExtensionNotFoundException if the mapping referenced a Component not registered
	 * @throws MappingExecutionException if an error happened during any internal operation
	 */
	public Set<TripleMapping> parseMapping(String content) throws IncompatibleMappingException, MappingExecutionException, IncorrectMappingException, ExtensionNotFoundException;


}
