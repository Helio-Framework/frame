package helio.framework.mappings.readers;

import helio.framework.mappings.Mapping;
import helio.framework.mappings.readers.exceptions.IncompatibleMappingException;
import helio.framework.mappings.readers.exceptions.IncorrectMappingException;

public interface MappingReader {

	
	
	public Mapping readMapping(String content) throws IncompatibleMappingException, IncorrectMappingException;
	
	
}
