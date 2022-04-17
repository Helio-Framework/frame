package helio.blueprints;

/**
 * This class codifies the types of {@link TranslationUnit} available
 * @author Andrea Cimmino
 *
 */
public enum UnitType {

	Sync,
	Scheduled, Async;
	
	
	public static boolean isAsync(TranslationUnit unit) {
		return Async.equals(unit.getUnitType());
	}
	
	public static boolean isSync(TranslationUnit unit) {
		return Sync.equals(unit.getUnitType());
	}
	
	public static boolean isScheduled(TranslationUnit unit) {
		return Scheduled.equals(unit.getUnitType());
	}
	
	
}