package helio.blueprints.exceptions;

public class HelioExecutionException  extends Exception{

	private static final long serialVersionUID = -5997433303607921591L;

	/**
	 * Constructor
	 * @param msg message to be printed
	 */
	public HelioExecutionException(String msg) {
		super(msg);
	}
}
