package org.getcomposer;

import org.getcomposer.entities.Autoload;
import org.getcomposer.entities.Distribution;
import org.getcomposer.entities.Source;

public abstract class AbstractPackage extends Resource {

	protected Autoload autoload = new Autoload();
	protected Distribution dist = new Distribution();
	protected Source source = new Source();
	
	/**
	 * Returns the <code>name</code> property.
	 * 
	 * @return the <code>name</code> value
	 */
	public String getName() {
		return getAsString("name");
	}
	
	/**
	 * Sets the <code>name</code> property.
	 * 
	 * @param name the new <code>name</code> value
	 */
	public void setName(String name) {
		set("name", name);
	}


	/**
	 * Returns the <code>type</code> property.
	 * 
	 * @return the <code>type</code> value
	 */
	public String getType() {
		return getAsString("type");
	}
	
	/**
	 * Sets the <code>type</code> property.
	 * 
	 * @param type new <code>type</code> value
	 */
	public void setType(String type) {
		set("type", type);
	}

	
	/**
	 * Returns the <code>description</code> property.
	 * @return
	 */
	public String getDescription() {
		return getAsString("description");
	}
	
	/**
	 * Sets the <code>description</code> property.
	 * 
	 * @param description the new <code>description</code> value
	 */
	public void setDescription(String description) {
		set("description", description);
	}
	
	/**
	 * Returns the version
	 * 
	 * @return the version
	 */
	public String getVersion() {
		return getAsString("version");
	}
	
	/**
	 * Sets the version
	 * 
	 */
	public void setVersion(String version) {
		set("version", version);
	}
	

	/**
	 * Returns the <code>autoload</code> entity.
	 * 
	 * @return the <code>autoload</code> entity
	 */
	public Autoload getAutoload() {
		return autoload;
	}
	
	/**
	 * Returns the <code>dist</code> entity.
	 * 
	 * @return the <code>dist</code> entity
	 */
	public Distribution getDist() {
		return dist;
	}
	
	/**
	 * Returns the <code>source</code> entity.
	 * 
	 * @return the <code>source</code> entity
	 */
	public Source getSource() {
		return source;
	}
	
}
