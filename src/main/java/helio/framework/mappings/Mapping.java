package helio.framework.mappings;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import helio.framework.mappings.datasources.Datasource;

/**
 * This class implements the mappings required by the {@link helio.framework.materialiser.MaterialiserEngine} to generate the RDF.<p> 
 * The {@link Mapping} consist on a list of {@link Datasource}, a list of {@link TranslationRules}, and a list of {@link LinkRule}.
 * @author Andrea Cimmino
 *
 */
public class Mapping extends AbstractJsonObject{

	@JsonProperty("datasources")
	private List<Datasource> datasources;
	@JsonProperty("rules")
	@JsonAlias("resource_rules")
	private List<TranslationRules> translationRules;
	@JsonProperty("links")
	@JsonAlias("link_rules")
	private List<LinkRule> linkRules;
	
	/**
	 * Initializes an empty mapping
	 */
	public Mapping() {
		datasources = new ArrayList<>();
		translationRules = new ArrayList<>();
		linkRules = new ArrayList<>();
	}
	
	/**
	 * This method merges the provided mapping with the one codified by this object
	 * @param mapping a {@link Mapping} to be merged with this
	 */
	public void merge(Mapping mapping) {
		datasources.addAll(mapping.getDatasources());
		translationRules.addAll(mapping.getTranslationRules());
		linkRules.addAll(mapping.getLinkRules());
	}
	
	public List<Datasource> getDatasources() {
		return datasources;
	}

	public void setDatasources(List<Datasource> datasources) {
		this.datasources = datasources;
	}

	public List<TranslationRules> getTranslationRules() {
		return translationRules;
	}

	public void setTranslationRules(List<TranslationRules> translationRules) {
		this.translationRules = translationRules;
	}
	
	public List<LinkRule> getLinkRules() {
		return linkRules;
	}

	public void setLinkRules(List<LinkRule> linkRules) {
		this.linkRules = linkRules;
	}

	// -- Ancillary

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datasources == null) ? 0 : datasources.hashCode());
		result = prime * result + ((linkRules == null) ? 0 : linkRules.hashCode());
		result = prime * result + ((translationRules == null) ? 0 : translationRules.hashCode());
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
		Mapping other = (Mapping) obj;
		if (datasources == null) {
			if (other.datasources != null)
				return false;
		} else if (!datasources.equals(other.datasources))
			return false;
		if (linkRules == null) {
			if (other.linkRules != null)
				return false;
		} else if (!linkRules.equals(other.linkRules))
			return false;
		if (translationRules == null) {
			if (other.translationRules != null)
				return false;
		} else if (!translationRules.equals(other.translationRules))
			return false;
		return true;
	}
	



	
}


