package org.getcomposer.repositories;

import java.net.URL;

import org.getcomposer.IOPackage;

public abstract class AbstractRepository extends IOPackage {

	private URL url;
	private String type;
	
	public AbstractRepository(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		firePropertyChange("url", this.url, this.url = url);
	}
	
}
