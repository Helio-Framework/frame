package helio.blueprints.exceptions;

import helio.blueprints.UnitBuilder;

/**
 * This exception is thrown when an unexpected error happens in the operations of a {@link UnitBuilder}. 
 * @author Andrea Cimmino Arriaga
 */
public class TranslationUnitExecutionException extends Exception{

	private static final long serialVersionUID = -8829060027475951733L;

	/**
	 * Constructor
	 * @param msg message to be printed
	 */
	public TranslationUnitExecutionException(String msg) {
		super(msg);
	}
}
