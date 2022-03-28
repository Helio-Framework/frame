package helio.blueprints.exceptions;

import helio.blueprints.components.MappingProcessor;

/**
 * This exception is thrown when a {@link MappingProcessor} detects a syntactical error.
 * @author Andrea Cimmino Arriaga
 */
public class IncorrectMappingException extends Exception {

	private static final long serialVersionUID = 6479261184926080050L;

	/**
	 * Constructor
	 * @param msg message to be printed
	 */
	public IncorrectMappingException(String msg) {
		super(msg);
	}
}
