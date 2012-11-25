package org.getcomposer.collection;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;

public abstract class JsonList<V> extends JsonCollection<V> implements Iterable<V> {
	
	protected List<V> values = new ArrayList<V>();

	protected JsonList(Type type) {
		super(type);
	}
	
	@SuppressWarnings("unchecked")
	protected void parse(Object obj) {
		clear();
		if (obj instanceof JSONArray) {
			List<V> oldValues = (List<V>) ((ArrayList<V>)values).clone();
			values = (List<V>)obj;
			firePropertyChange("values", oldValues, values);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.values.JsonCollection#size()
	 */
	public int size() {
		return values.size();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.collection.JsonCollection#clear()
	 */
	public void clear() {
		values.clear();
	}
	
	/**
	 * Checks whether the value is present.
	 * 
	 * @param value the value to check
	 * @return true if present, false if not
	 */
	public boolean has(V value) {
		return values.contains(value);
	}
	
	/**
	 * Returns the passed object
	 * 
	 * @param packageName
	 * @return the dependency
	 */
	public V get(int index) {
		return values.get(index);
	}
	
	/**
	 * Adds an value to this values
	 * 
	 * @param value the new value
	 */
	@SuppressWarnings("unchecked")
	public void add(V value) {
		List<V> oldValues = (List<V>) ((ArrayList<V>)values).clone();
		values.add(value);
		firePropertyChange("values", oldValues, values);
	}
	
	/**
	 * Removes an value from this values
	 * 
	 * @param value the value to remove
	 */
	@SuppressWarnings("unchecked")
	public void remove(V value) {
		List<V> oldValues = (List<V>) ((ArrayList<V>)values).clone();
		values.remove(value);
		firePropertyChange("values", oldValues, values);
	}
	
	@SuppressWarnings("unchecked")
	public V[] toArray() {
		return (V[]) values.toArray();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<V> iterator() {
		return values.iterator();
	}
}
