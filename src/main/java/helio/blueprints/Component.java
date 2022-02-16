package helio.blueprints;

import java.util.Objects;

public class Component {


	private String source;

	private String clazz;

	private ComponentType type;

	
	public Component() {
		super();
	}
		
	/**
	 * Constructor of {@link Component}
	 * @param source the path to the jar, it can be a URL or a local directory
	 * @param clazz the full class specification, e.g., components.external.handlers.JsonHandler
	 * @param type one of the types available at {@link ComponentType}
	 */
	public Component(String source, String clazz, ComponentType type) {
		super();
		this.source = source;
		this.clazz = clazz;
		this.type = type;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public ComponentType getType() {
		return type;
	}
	public void setType(ComponentType type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		return Objects.hash(clazz, source, type);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Component other = (Component) obj;
		return Objects.equals(clazz, other.clazz) && Objects.equals(source, other.source)
				&& Objects.equals(type, other.type);
	}

}
