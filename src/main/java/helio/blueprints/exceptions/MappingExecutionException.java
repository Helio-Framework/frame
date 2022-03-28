package helio.blueprints.exceptions;

import helio.blueprints.components.MappingProcessor;

/**
 * This exception is thrown when an unexpected error happens in the operations of a {@link MappingProcessor}. 
 * @author Andrea Cimmino Arriaga
 */
public class MappingExecutionException extends Exception{

	private static final long serialVersionUID = -8829060027475951733L;

	/**
	 * Constructor
	 * @param msg message to be printed
	 */
	public MappingExecutionException(String msg) {
		super(msg);
	}
}
