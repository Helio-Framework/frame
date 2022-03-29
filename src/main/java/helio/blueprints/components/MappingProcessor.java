package helio.blueprints.components;

import java.util.Set;

import helio.blueprints.exceptions.ExtensionNotFoundException;
import helio.blueprints.exceptions.IncompatibleMappingException;
import helio.blueprints.exceptions.IncorrectMappingException;
import helio.blueprints.exceptions.MappingExecutionException;

public interface MappingProcessor {

	/**
	 * This method receives a representation of a mapping and returns a set of instantiated {@link TranslationUnit}
	 * @param content a representation of a mapping
	 * @return a set of {@link TranslationUnit}s
	 * @throws IncompatibleMappingException is thrown when the representation is not compatible with a {@link MappingProcessor}
	 * @throws IncorrectMappingException if the representation of a mapping has syntax errors
	 * @throws ExtensionNotFoundException if the mapping referenced a Component not registered
	 * @throws MappingExecutionException if an error happened during any internal operation
	 */
	public Set<TranslationUnit> parseMapping(String content) throws IncompatibleMappingException, MappingExecutionException, IncorrectMappingException, ExtensionNotFoundException;


}
