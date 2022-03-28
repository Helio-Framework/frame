package helio.blueprints.exceptions;

import helio.blueprints.components.MappingProcessor;

/**
 * This exception is thrown when a {@link MappingProcessor} detects something that not compatible with its mapping language. In other words, the {@link MappingProcessor} is not able to process a provided mapping, although such mapping can be syntactically correct for another language.
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
