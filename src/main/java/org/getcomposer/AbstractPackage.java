package org.getcomposer;


public abstract class AbstractPackage extends Resource {

	/**
	 * Returns the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return getAsString("name");
	}
	
	/**
	 * Sets the name
	 * 
	 * @param name the name to set
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public <T> T setName(String name) {
		set("name", name);
		return (T)this;
	}


	/**
	 * Returns the type
	 * 
	 * @return the type
	 */
	public String getType() {
		return getAsString("type");
	}
	
	/**
	 * Sets the type
	 * 
	 * @param type the type to set
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public <T> T setType(String type) {
		set("type", type);
		return (T)this;
	}

	
	/**
	 * Returns the description
	 * @return
	 */
	public String getDescription() {
		return getAsString("description");
	}
	
	/**
	 * Sets the description
	 * 
	 * @param description the description to set
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public <T> T setDescription(String description) {
		set("description", description);
		return (T)this;
	}
}
