package helio.framework.mappings.readers;

import java.util.ArrayList;
import java.util.List;
import helio.framework.mappings.Mapping;
import helio.framework.mappings.readers.exceptions.IncompatibleMappingException;
import helio.framework.mappings.readers.exceptions.IncorrectMappingException;

public class Mappings {

	private static List<MappingReader> readers = new ArrayList<>();
	
	static {
		addMappingReader(new JsonReader());
		addMappingReader(new RmlReader());
	}
	
	public static void addMappingReader(MappingReader reader) {
		if(readers.contains(reader))
			throw new IllegalArgumentException("The provided MappingReader already exists");
		readers.add(reader);
	}
	
	public static Mapping readMapping(String rawMapping) {
		Mapping mapping = null;
		for(int index=0; index < readers.size(); index++) {
			try {
				mapping = readers.get(index).readMapping(rawMapping);
				break;
			} catch (IncompatibleMappingException e) {
				System.out.println("[WARNING] mapping not compatible with "+readers.get(index).getClass().getCanonicalName());
			} catch( IncorrectMappingException e) {
				System.out.println("Mapping has syntax errors");
				e.toString();
			}
		}
		return mapping;
	}
	
	
	
	
}
