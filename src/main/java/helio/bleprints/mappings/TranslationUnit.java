package helio.bleprints.mappings;



import java.io.InputStream;


public interface TranslationUnit {

	public String getId();

	public UnitType getUnitType();

	public void translate();

	public void translate(InputStream stream);

	public boolean generatesSubject(String subject);
}
