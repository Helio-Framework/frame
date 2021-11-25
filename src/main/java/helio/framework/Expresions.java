package helio.framework;

import java.util.ArrayList;
import java.util.List;


public class Expresions {

	// -- Attributes
	
	private static final char EXPRESION_TOKEN_BEGIN = '{';
	private static final char EXPRESION_TOKEN_END = '}';
	private static final char EXPRESION_TOKEN_IGNORE = '\\';

	// -- Constructors

	private Expresions() {
		super();
	}
	


	// -- Factory methods

	public static List<String> extractDataReferences(String expression) {
		List<String> dataReferences = new ArrayList<>();
		if (expression == null || expression.isEmpty())
			throw new IllegalArgumentException("Expression provided in constructor must not be null");
		
		if(!expression.isEmpty())
			findDataReferences(expression, dataReferences );
			
		return dataReferences;
	}
	

	/**
	 * <p>This method returns a list of raw data access filters/references within and
	 * IRI. A filter/reference can be an XPath expression, a JSONPath expression,...
	 * </p>
	 * <p>
	 * The reference is found by looking up the characters '{' and '}' one by one,
	 * and only those expressions enclosed in the first level of nesting are
	 * retrieved. This makes possible to write nested '{' '}' characters without
	 * causing any trouble.</p>
	 *
	 */
	private static void findDataReferences(String expression, List<String> dataReferences) {
		int startingIndex = -1;
		char[] expressionChars = expression.toCharArray();
		int ignores = 0;
		for (int index = 0; index < expressionChars.length; index++) {
			char charAt = expressionChars[index];
			if (charAt == EXPRESION_TOKEN_BEGIN && ignores == 0) { // found a '{'
				startingIndex = index;
				continue;
			} else if (charAt == EXPRESION_TOKEN_END && ignores == 0 && startingIndex != -1) { // found a '}'
				dataReferences.add(expression.substring(startingIndex+1, index));
				startingIndex = -1;
				continue;
			}
			if (charAt == EXPRESION_TOKEN_IGNORE) {
				ignores++;
			} else {
				ignores = 0;
			}
		}
	}

	
	
	


}
