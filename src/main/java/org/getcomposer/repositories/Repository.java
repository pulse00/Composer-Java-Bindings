package org.getcomposer.repositories;

import org.getcomposer.Resource;

public abstract class Repository extends Resource {

	public Repository(String type) {
		set("type", type);
	}

	/**
	 * Returns the <code>type</code> property.
	 * 
	 * @return the <code>type</code> property
	 */
	public String getType() {
		return getAsString("type");
	}

	public String getUrl() {
		return getAsString("url");
	}

	public void setUrl(String url) {
		set("url", url);
	}
	
}
