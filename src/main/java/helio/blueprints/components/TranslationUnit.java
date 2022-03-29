package helio.blueprints.components;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;

/**
 * This component is responsible of translating data from hetergeneous data sources into RDF.
 * @author Andrea Cimmino
 *
 */
public interface TranslationUnit{

	
	/**
	 * This method provides the {@link UnitType} of a {@link TranslationUnit}
	 * @return a {@link UnitType}
	 */
	public UnitType getUnitType();

	/**
	 * This method translates heterogeneous data into RDF synchronously or scheduled
	 */
	public Model translate();

	/**
	 * This method translates heterogeneous data into RDF asynchronously
	 */
	public void translate(InputStream stream);

	/**
	 * This method returns a {@link Model} containing RDF generated asynchronously or scheduled 
	 * @return
	 */
	public Model getRDF();
		
	public Integer getScheduledTime();
	
}
