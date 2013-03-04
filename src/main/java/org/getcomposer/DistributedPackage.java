package org.getcomposer;

import java.util.Arrays;
import java.util.LinkedList;

import org.getcomposer.entities.Autoload;
import org.getcomposer.entities.Version;
import org.getcomposer.entities.Distribution;
import org.getcomposer.entities.Source;
import org.json.simple.JSONObject;

public abstract class DistributedPackage extends VersionedPackage {

	protected Autoload autoload = new Autoload();
	protected Distribution dist = new Distribution();
	protected Source source = new Source();
	protected transient Version detailedVersion = null;
	
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {

			JSONObject json = (JSONObject)obj;

			// parsed from super:
			// name, description, type, version
			
			parseField(json, "autoload");
			parseField(json, "dist");
			parseField(json, "source");
			
		}
		
		super.parse(obj);
	}
	
	@Override
	public Object prepareJson(LinkedList<String> fields) {
		String[] before = new String[]{"name", "description", "type", "version"};
		fields.addAll(0, new LinkedList<String>(Arrays.asList(before)));
		
		String[] after = new String[]{"autoload", "dist", "source"};
		fields.addAll(new LinkedList<String>(Arrays.asList(after)));
		return super.prepareJson(fields);
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
