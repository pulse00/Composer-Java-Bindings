package org.getcomposer.core.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;


public abstract class AbstractJsonArray<V> extends JsonEntity implements JsonCollection, Iterable<V> {
	
	protected List<V> values = new ArrayList<V>();
	
	private transient PropertyChangeListener propListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent e) {
			int index = values.indexOf(e.getSource());
			firePropertyChange("#" + index + "." + e.getPropertyName(), e.getOldValue(), e.getNewValue());
		}
	};
	
	@SuppressWarnings("unchecked")
	protected void parse(Object obj) {
		clear();
		if (obj instanceof JSONArray) {
			List<V> oldValues = (List<V>) ((ArrayList<V>)values).clone();
			values = (List<V>)obj;
			firePropertyChange("values", oldValues, values);
		}
	}

	public Object prepareJson(LinkedList<String> fields) {
		LinkedList<Object> out = new LinkedList<Object>();
		for (V val : values) {
			if (val == null) {
				continue;
			}
			out.add(prepareJsonValue(val));
		}
		return out;
	}

	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.core.entities.JsonCollection#size()
	 */
	public int size() {
		return values.size();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.core.entities.JsonCollection#clear()
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
		
		if (value instanceof JsonEntity) {
			((JsonEntity)value).addPropertyChangeListener(propListener);
		}
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
		
		if (value instanceof JsonEntity) {
			((JsonEntity)value).removePropertyChangeListener(propListener);
		}
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
