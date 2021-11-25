package helio.framework.mappings;

import com.fasterxml.jackson.core.JsonProcessingException;

import helio.framework.FrameworkUtils;

public abstract class AbstractJsonObject {

	@Override
	public String toString() {
		try {
			return FrameworkUtils.JACKSON_MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toJson() throws JsonProcessingException {
		return FrameworkUtils.JACKSON_MAPPER.writeValueAsString(this);
	}
}
