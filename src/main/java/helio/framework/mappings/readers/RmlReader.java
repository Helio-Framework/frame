package helio.framework.mappings.readers;

import helio.framework.mappings.Mapping;
import helio.framework.mappings.readers.exceptions.IncompatibleMappingException;

public class RmlReader implements MappingReader{

	@Override
	public Mapping readMapping(String content) throws IncompatibleMappingException {
		throw new IncompatibleMappingException("Mapping is not compatible with RML");
	
	}

}
