package helio.framework.velocity;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import helio.framework.Expresions;
import helio.framework.mappings.TranslationRule;
import helio.framework.mappings.TranslationRules;

//TODO: check that lang and datatype can also be created from data references
public class VelocityMapper {

	private static final String SUBJECT_VARIABLE_PREAMBLE = "#set( $subject = \"";
	private static final String PREDICATE_VARIABLE_PREAMBLE = "#set( $predicate";
	private static final String OBJECT_VARIABLE_PREAMBLE = "#set( $object";
	private static final String VARIABLE_POSTAMBLE = "\" )\n";

	private static final String VARIABLE_ASSIGNMENT = "=\"";
	private static final String END_OF_TRIPLET = " . \n";
	private static final String LITERAL_TOKEN = "$HF.quote()";
	private static final char URI_OPEN_TOKEN = '<';
	private static final String URI_DATATYPE_OPEN_TOKEN = "^^<";
	private static final char URI_CLOSE_TOKEN = '>';
	private static final char KEY_OPEN_TOKEN = '{';
	private static final char KEY_CLOSE_TOKEN = '}';
	private static final char AT_TOKEN = '@';
	private static final String VELOCITY_VARIABLE_OPEN_TOKEN = "${";
	private static final String VELOCITY_FOR_PREAMBLE = " #foreach( ${";
	private static final String VELOCITY_FOR_MIDAMBLE_1 = "} in ${";
	private static final String VELOCITY_FOR_MIDAMBLE_2 = "SET}) ";
	private static final String VELOCITY_FOR_POSTAMBLE = " #end";
	private static final String VELOCITY_FOR_POSTAMBLE_NEWLINE = "#end\n";
	private static final String IF_STATEMENT_PREAMBLE = "#if( $HF.notBlank($subject) && $HF.notBlank($predicate";
	private static final String IF_STATEMENT_MIDAMBLE = ") && $HF.notBlank($object";
	private static final String IF_STATEMENT_POSTAMBLE = 	") )\n";
	private static final String TRIPLET_PREAMBLE = "\t $subject $predicate";
	private static final String TRIPLET_POSTAMBLE = " $object";
	
	// -- Constructor
	private VelocityMapper() {
		super();
	}

	
	// TODO: change to protected
	public static String toVelocityTemplate(TranslationRules rules) {
		StringBuilder triplet = new StringBuilder();
		List<String> subjectReferences = Expresions.extractDataReferences(rules.getSubject());
		String subject = VelocityMapper.computeVelocityTemplate4Rule(rules.getSubject(), subjectReferences, null);
		triplet.append(SUBJECT_VARIABLE_PREAMBLE).append(subject).append(VARIABLE_POSTAMBLE);
		rules.getProperties().stream()
							 .map(VelocityMapper::toVelocityTemplate)
							 .forEach(elem -> triplet.append(elem));

		return triplet.toString();
	}


	private static String toVelocityTemplate(TranslationRule rule) {
		StringBuilder builder = new StringBuilder();
		List<String> dataReferencesPredicate =  Expresions.extractDataReferences(rule.getPredicate());
		String predicate = computeVelocityTemplate4Rule(rule.getPredicate(), dataReferencesPredicate, null);

		List<String> dataReferencesObject =  Expresions.extractDataReferences(rule.getObject());
		String object = computeVelocityTemplate4Rule(rule.getObject(), dataReferencesObject, rule);
		String predicateHash =  normalisedHashReference(predicate);
		String objectHash = normalisedHashReference(object);
		builder.append(PREDICATE_VARIABLE_PREAMBLE).append(predicateHash).append(VARIABLE_ASSIGNMENT).append(predicate).append(VARIABLE_POSTAMBLE);
		builder.append(OBJECT_VARIABLE_PREAMBLE).append(objectHash).append(VARIABLE_ASSIGNMENT).append(object).append(VARIABLE_POSTAMBLE);
		builder.append(IF_STATEMENT_PREAMBLE).append(predicateHash).append(IF_STATEMENT_MIDAMBLE).append(objectHash).append(IF_STATEMENT_POSTAMBLE);
		builder.append(TRIPLET_PREAMBLE).append(predicateHash).append(TRIPLET_POSTAMBLE).append(objectHash).append(END_OF_TRIPLET);
		builder.append(VELOCITY_FOR_POSTAMBLE_NEWLINE);
		
		return builder.toString();
	}

	private static String computeVelocityTemplate4Rule(String template, List<String> dataReferences, TranslationRule rule) {
		StringBuilder velocityTemplate = new StringBuilder();
		if(dataReferences.isEmpty()) {
			if(rule!=null && rule.getIsLiteral()) {
				velocityTemplate.append(LITERAL_TOKEN).append(template).append(LITERAL_TOKEN);
				String datatype = rule.getDataType();
				String lang = rule.getLanguage();
				if(datatype!=null && !datatype.isEmpty())
					velocityTemplate.append(URI_DATATYPE_OPEN_TOKEN).append(datatype).append(URI_CLOSE_TOKEN);
				if(lang!=null && !lang.isEmpty())
					velocityTemplate.append(AT_TOKEN).append(lang);
				if(lang!=null && datatype!=null)
					System.out.println("Somethign wrong in the mappings");
			}else {
				//TODO: add default management of syntax incorrect uris
				velocityTemplate.append(URI_OPEN_TOKEN).append(template).append(URI_CLOSE_TOKEN);
			}
		}else {
			// Switch in template the old reference for the normalized one
			String reference = dataReferences.remove(0);
			String normalisedReference = normaliseReference(reference,false);
			String replacedTemplate = StringUtils.replace(template, wrapString(reference,KEY_OPEN_TOKEN,KEY_CLOSE_TOKEN), wrapString(normalisedReference,VELOCITY_VARIABLE_OPEN_TOKEN,KEY_CLOSE_TOKEN));
			velocityTemplate.append(VELOCITY_FOR_PREAMBLE).append(normalisedReference).append(VELOCITY_FOR_MIDAMBLE_1).append(normalisedReference).append(VELOCITY_FOR_MIDAMBLE_2);
			velocityTemplate.append(computeVelocityTemplate4Rule(replacedTemplate, dataReferences, rule));
			velocityTemplate.append(VELOCITY_FOR_POSTAMBLE);
		}
		return velocityTemplate.toString();
	}


	private static final String VELOCITY_VARIABLE_PREAMBLE = "Hash";
	private static final String VELOCITY_VARIABLE_SET = "SET";
	protected static String normaliseReference(String reference, Boolean isSet) {
		StringBuilder normalisedReference = new StringBuilder();
		String hash = normalisedHashReference(reference);
		normalisedReference.append(VELOCITY_VARIABLE_PREAMBLE);
		normalisedReference.append(hash);
		if(isSet)
			normalisedReference.append(VELOCITY_VARIABLE_SET);
		return normalisedReference.toString();
	}
	
	protected static String normalisedHashReference(String reference) {
		int hash = reference.hashCode();
		if(hash<0)
			hash = hash*(-1);
		return String.valueOf(hash);
	}


	// Wrapping methods

	private static String wrapString(String string, char head, char tail) {
		StringBuilder str = new StringBuilder();
		str.append(head).append(string).append(tail);
		return str.toString();
	}

	private static String wrapString(String string, String head, char tail) {
		StringBuilder str = new StringBuilder();
		str.append(head).append(string).append(tail);
		return str.toString();
	}
}
