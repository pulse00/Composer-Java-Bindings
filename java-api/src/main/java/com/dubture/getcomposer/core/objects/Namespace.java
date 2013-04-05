package com.dubture.getcomposer.core.objects;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.dubture.getcomposer.core.collection.UniqueJsonArray;

/**
 * Represents a namespace entry in the psr0 entity of a composer package.
 * 
 * @see http://getcomposer.org/doc/04-schema.md#psr-0
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Namespace extends JsonObject {

	private transient UniqueJsonArray paths = new UniqueJsonArray();
	
	public Namespace() {
		listen();
		paths.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				firePropertyChange(getNamespace() +"."+ evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
			}
		});
	}
	
	public Namespace(String namespacename, String path) {
		this();
		setNamespace(namespacename);
		add(path);
	}
	
	@Override
	public String prepareJson(LinkedList<String> fields) {
		return paths.toJson();
	}
	
	@Override
	protected List<String> getOwnProperties() {
		String[] props = new String[]{"paths"};
		List<String> list = new ArrayList<String>(Arrays.asList(props));
		list.addAll(super.getOwnProperties());
		return list;
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return the name
	 */
	public String getNamespace() {
		return getAsString("namespace");
	}
	
	/**
	 * Sets the name.
	 * 
	 * @param name the name to set
	 */
	public void setNamespace(String namespace) {
		set("namespace", namespace);
	}
	
	public void add(String path) {
		paths.add(path);
	}
	
	/**
	 * Clears the paths from this namespace
	 */
	public void clear() {
		paths.clear();
	}
	
	public void addPaths(UniqueJsonArray paths) {
		for (Object path : paths) {
			if (!has((String)path)) {
				add((String)path);
			}
		}
	}
	
	/**
	 * Returns the path and if there are more than one, returns the first one.
	 * 
	 * @return the version
	 */
	public String getFirst() {
		return (String) paths.get(0);
	}
	
	public void remove(String path) {
		paths.remove(path);
	}
	
	public void removeAll() {
		paths.clear();
	}
	
	public UniqueJsonArray getPaths() {
		return paths;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dubture.getcomposer.core.entities.AbstractJsonObject#size()
	 */
	public int size() {
		return paths.size();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Namespace clone() {
		Namespace clone = new Namespace();
		cloneProperties(clone);
		clone.addPaths(paths);
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Namespace) {
			Namespace namespace = (Namespace) obj;
			return namespace == this 
					|| (getNamespace() == null 
							? namespace.getNamespace() == null
							: getNamespace().equals(namespace.getNamespace()))
						&& getPaths().equals(namespace.getPaths());
		}
		return false;
	}
}
