package helio.blueprints;

public enum ComponentType {

	PROVIDER("DataProvider"), HANDLER("DataHandler"), PROCESSOR("MappingProcessor"), FUNCTION("MappingFunction"), UNIT("TranslationUnit");

	private final String componentType;

	private ComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentType() {
		return componentType;
	}
}
