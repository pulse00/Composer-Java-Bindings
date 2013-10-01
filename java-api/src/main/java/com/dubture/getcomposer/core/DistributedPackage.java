package com.dubture.getcomposer.core;

import com.dubture.getcomposer.core.entities.Version;
import com.dubture.getcomposer.core.objects.Autoload;
import com.dubture.getcomposer.core.objects.Distribution;
import com.dubture.getcomposer.core.objects.Source;

public abstract class DistributedPackage extends VersionedPackage {

	protected Autoload autoload = new Autoload();
	protected Distribution dist = new Distribution();
	protected Source source = new Source();
	protected transient Version detailedVersion = null;

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
