package org.getcomposer.core.repositories;

import java.util.Arrays;
import java.util.LinkedList;

import org.getcomposer.core.MinimalPackage;

public abstract class Repository extends MinimalPackage {

	public Repository(String type) {
		set("type", type);
	}
	
	@Override
	public Object prepareJson(LinkedList<String> fields) {
		String[] before = new String[]{"type", "url"};
		fields.addAll(0, new LinkedList<String>(Arrays.asList(before)));
		return super.prepareJson(fields);
	}

	/**
	 * Returns the <code>type</code> property.
	 * 
	 * @return the <code>type</code> property
	 */
	public String getType() {
		return getAsString("type");
	}

	/**
	 * Returns the <code>url</code> property.
	 * 
	 * @return the <code>url</code> property
	 */
	public String getUrl() {
		return getAsString("url");
	}

	public void setUrl(String url) {
		set("url", url);
	}
}
