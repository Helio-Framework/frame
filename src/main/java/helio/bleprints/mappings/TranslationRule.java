package helio.bleprints.mappings;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * This object represents a rule that generates from heterogeneous data an RDF triple
 * @author Andrea Cimmino
 * @email banse27@gmail.com, cimmino@fi.upm.es
 */
public class TranslationRule {

	// -- Attributes
	@Expose
	protected String object; // mandatory
	@Expose
	protected String predicate; // mandatory
	@Expose
	@SerializedName(value = "literal", alternate = "is_literal")
	protected Boolean isLiteral; // mandatory
	@Expose
	@SerializedName(value = "datatype")
	protected String dataType; // optional; disjoint with language
	@Expose
	@SerializedName(value = "lang")
	protected String language; // optional; disjoint with language

	// -- Constructor

	public TranslationRule() {
		super();
	}

	// -- Getters and Setters

	public String getObject() {
		return object;
	}


	public void setObject(String expression) {
		this.object = expression;
	}


	public String getPredicate() {
		return predicate;
	}


	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Boolean getIsLiteral() {
		return isLiteral;
	}

	public void setIsLiteral(Boolean isLiteral) {
		this.isLiteral = isLiteral;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}


	// Other methods

	public List<String> fetchDataReferences(){
		List<String> dataReferences = new ArrayList<>();
		dataReferences.addAll(Expresions.extractDataReferences(predicate));
		dataReferences.addAll(Expresions.extractDataReferences(object));
		if(dataType!=null && !dataType.isEmpty())
			dataReferences.addAll(Expresions.extractDataReferences(dataType));
		if(language!=null && !language.isEmpty())
			dataReferences.addAll(Expresions.extractDataReferences(language));
		return dataReferences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
		result = prime * result + ((isLiteral == null) ? 0 : isLiteral.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + ((predicate == null) ? 0 : predicate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		TranslationRule other = (TranslationRule) obj;
		if (dataType == null) {
			if (other.dataType != null)
				return false;
		} else if (!dataType.equals(other.dataType))
			return false;
		if (isLiteral == null) {
			if (other.isLiteral != null)
				return false;
		} else if (!isLiteral.equals(other.isLiteral))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		if (predicate == null) {
			if (other.predicate != null)
				return false;
		} else if (!predicate.equals(other.predicate))
			return false;
		return true;
	}












}
