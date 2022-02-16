package helio.blueprints;

public enum ComponentType {

	PROVIDER("DataProvider"), HANDLER("DataHandler"), READER("MappingReader"), FUNCTION("MappingFunction");

	private final String componentType;

	private ComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentType() {
		return componentType;
	}
}
