package org.getcomposer.collection;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public abstract class JsonMap<C, V> extends JsonCollection<V> {

	protected Map<String, V> collection = new HashMap<String, V>();
	
	protected JsonMap(Type type) {
		super(type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.collection.JsonCollection#size()
	 */
	public int size() {
		return collection.size();
	}
	
	/**
	 * Returns whether a key is present in this collection
	 * 
	 * @param key the key
	 * @return
	 */
	public boolean has(String key) {
		return collection.containsKey(key);
	}
	
	/**
	 * Returns the object
	 * 
	 * @param the key
	 * @return the object
	 */
	public V get(String key) {
		return collection.get(key);
	}
	
	/**
	 * Adds a new dependency to this collection
	 * 
	 * @param key the key
	 * @param item the item
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public C add(String key, V item) {
		Map<String, V> oldCollection = (Map<String, V>) ((HashMap<String, V>)collection).clone();
		collection.put(key, item);
		firePropertyChange("collection", oldCollection, collection);
		return (C)this;
	}
	
	/**
	 * Removes an item from this collection
	 * 
	 * @param key the key
	 */
	@SuppressWarnings("unchecked")
	public C remove(String key) {
		Map<String, V> oldCollection = (Map<String, V>) ((HashMap<String, V>)collection).clone();
		collection.remove(key);
		firePropertyChange("collection", oldCollection, collection);
		return (C)this;
	}

}
