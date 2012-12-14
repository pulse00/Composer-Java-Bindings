package org.getcomposer;

import java.util.Arrays;
import java.util.LinkedList;

import org.getcomposer.entities.GenericEntity;


/**
 * A package that can be read from a file or string and also dump to the latter one.
 * 
 * @author Thomas Gossmann <gos.si>
 * 
 */
public class MinimalPackage extends GenericEntity {
	

	@Override
	public Object prepareJson(LinkedList<String> fields) {
		String[] before = new String[]{"name", "description"};
		fields.addAll(0, new LinkedList<String>(Arrays.asList(before)));
		return super.prepareJson(fields);
	}
	
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
}
