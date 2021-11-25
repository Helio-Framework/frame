package helio.framework.mappings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import helio.framework.Expresions;
import helio.framework.FrameworkUtils;


/**
 * This object represents a group of {@link TranslationRule} to generate a piece of RDF
 * @author Andrea Cimmino
 *
 */
public class TranslationRules extends AbstractJsonObject {
	
	// -- Attributes
	@JsonProperty("id")  
	private String id; // An Id that identifies this object
	
	@JsonProperty("subject") 
	private String subject; // if contains '{...}' then is a template otherwise is constant
	
	@JsonProperty 
	private List<TranslationRule> properties; 
	
	@JsonProperty("datasources")
	@JsonAlias("datasource_ids")
	private Set<String> datasourcesId; 
	
	
	// -- Constructor
	
	public TranslationRules() {
		super();
		properties = new ArrayList<>();
		datasourcesId = new HashSet<>();
	}
	
	public static TranslationRules create(String jsonRepresentation) throws JsonMappingException, JsonProcessingException {
		return FrameworkUtils.JACKSON_MAPPER.readValue(jsonRepresentation, TranslationRules.class);
	}
	
	// -- Getters & Setters

	public Boolean hasDataSourceId(String dataSourceId) {
		return datasourcesId.contains(dataSourceId);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String resourceRuleId) {
		this.id = resourceRuleId;
	}

	public String getSubject() {
		return subject;
	}


	public void setSubject(String subjectTemplate) {
		this.subject = subjectTemplate;
	}

	public List<TranslationRule> getProperties() {
		return properties;
	}

	public void setProperties(List<TranslationRule> properties) {
		this.properties = properties;
	}
	
	public Set<String> getDatasourcesId() {
		return datasourcesId;
	}

	public void setDatasourcesId(Set<String> datasourcesId) {
		this.datasourcesId = datasourcesId;
	}
	
	// -- Other methods
	public Set<String> fetchDataReferences(){
		Set<String> dataReferences = this.properties.stream().flatMap(elem -> elem.fetchDataReferences().stream()).collect(Collectors.toSet());
		dataReferences.addAll(Expresions.extractDataReferences(this.subject));
		return dataReferences;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranslationRules other = (TranslationRules) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
	
	
	
}
