package helio.blueprints.components;

public enum ComponentType {

	PROVIDER("DataProvider"), HANDLER("DataHandler"), BUILDER("UnitBuilder"), FUNCTION("MappingFunction"), UNIT("TranslationUnit");
	private String componentType;
	private ComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentType() {
		return componentType;
	}
}
