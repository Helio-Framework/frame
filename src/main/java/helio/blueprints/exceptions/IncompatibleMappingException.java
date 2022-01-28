package helio.blueprints.exceptions;

import helio.bleprints.mappings.Mapping;
import helio.blueprints.components.MappingReader;

/**
 * This exception is thrown when a {@link Mapping} is not compatible with a {@link MappingReader}. In other words, the {@link MappingReader} is not able to process the {@link Mapping}, although the {@link Mapping} can be syntactically correct.
 * @author Andrea Cimmino Arriaga
 */
public class IncompatibleMappingException extends Exception{

	private static final long serialVersionUID = -8829060027475951733L;

	/**
	 * Constructor
	 * @param msg message to be printed
	 */
	public IncompatibleMappingException(String msg) {
		super(msg);
	}
}
