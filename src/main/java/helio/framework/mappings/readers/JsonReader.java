package helio.framework.mappings.readers;

import helio.framework.FrameworkUtils;
import helio.framework.mappings.Mapping;

public class JsonReader implements MappingReader {

	
	@Override
	public Mapping readMapping(String content) {
		try {
			return FrameworkUtils.JACKSON_MAPPER.readValue(content, Mapping.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
