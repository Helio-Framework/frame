package helio.blueprints;

import java.util.Set;

import helio.blueprints.exceptions.ExtensionNotFoundException;
import helio.blueprints.exceptions.IncompatibleMappingException;
import helio.blueprints.exceptions.IncorrectMappingException;
import helio.blueprints.exceptions.TranslationUnitExecutionException;

public interface UnitBuilder extends ConfigurableBlueprint {

	/**
	 * This method receives a representation of a mapping and returns a set of instantiated {@link TranslationUnit}
	 * @param content a representation of a mapping
	 * @return a set of {@link TranslationUnit}s
	 * @throws IncompatibleMappingException is thrown when the representation is not compatible with a {@link UnitBuilder}
	 * @throws IncorrectMappingException if the representation of a mapping has syntax errors
	 * @throws ExtensionNotFoundException if the mapping referenced a Component not registered
	 * @throws TranslationUnitExecutionException if an error happened during any internal operation
	 */
	public Set<TranslationUnit> parseMapping(String content) throws IncompatibleMappingException, TranslationUnitExecutionException, IncorrectMappingException, ExtensionNotFoundException;


}
