package helio.blueprints;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * The {@link TranslationUnit} implementations are responsible of providing executable tasks that translate data from heterogeneous data sources into RDF.
 * @author Andrea Cimmino Arriaga
 *
 */

public interface TranslationUnit extends ConfigurableBlueprint {
	
	/**
	 * This method retrieves the time that a {@link TranslationUnit} is run periodically. Uniquely {@link TranslationUnit} of {@link UnitType} Scheduled report a non null value. 
	 * @return the scheduled time in milliseconds or null if the {@link TranslationUnit} is not {@link UnitType} Scheduled
	 */
	public Integer getScheduledTime();
	
	/**
	 * This method sets the time that a {@link TranslationUnit} is run periodically. Setting this value make only sense if the {@link TranslationUnit} is {@link UnitType} Scheduled.
	 * @param ms the new time in milliseconds to run periodically this {@link TranslationUnit} 
	 */
	public void setScheduledTime(Integer ms);
		
	/**
	 * This method returns the {@link UnitType} of a {@link TranslationUnit}  
	 * @return one of Scheduled, Sync, or Async
	 */
	public UnitType getUnitType();
	
	public void setUnitType(UnitType type);


	public String getId();


	public Callable<Void> getTask();
	
	public List<String> getTranslations();
	
	public void flush();
	
}
