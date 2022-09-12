package helio.blueprints;

import java.util.concurrent.Callable;
import java.util.Map;
import helio.blueprints.exceptions.TranslationUnitExecutionException;

/**
 * The {@link TranslationUnit} implementations are responsible of providing executable tasks that translate data from heterogeneous data sources into RDF.
 * @author Andrea Cimmino Arriaga
 *
 */

public interface TranslationUnit extends ConfigurableBlueprint {
	
	
	/**
	 * This method returns the {@link UnitType} of a {@link TranslationUnit}  
	 * @return one of Scheduled, Sync, or Async
	 */
	public UnitType getUnitType();
	
	public void setUnitType(UnitType type);

	public String getId();

	public Callable<String> getTask(Map<String,Object> arguments) throws TranslationUnitExecutionException;

	public int getScheduledTime();
	
	public void setScheduledTime(int scheduledTime);
}
