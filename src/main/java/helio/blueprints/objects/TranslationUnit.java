package helio.blueprints.objects;



import java.io.InputStream;


import helio.blueprints.mappings.Datasource;

/**
 * This component is responsible of translating data from hetergeneous data sources into RDF.
 * @author Andrea Cimmino
 *
 */
public interface TranslationUnit {

	/**
	 * This method provides the id of a {@link TranslationUnit}
	 * @return the id of the {@link TranslationUnit}
	 */
	public String getId();

	/**
	 * This method provides the {@link UnitType} of a {@link TranslationUnit}
	 * @return a {@link UnitType}
	 */
	public UnitType getUnitType();

	/**
	 * This method translates heterogeneous data into RDF synchronously or scheduled
	 */
	public void translate();

	/**
	 * This method translates heterogeneous data into RDF asynchronously
	 */
	public void translate(InputStream stream);

	/**
	 * This method retrieves the associated {@link Datasource} of a TranslationUnit
	 * @return a {@link Datasource} 
	 */
	public Datasource getDatasource();
	
}
