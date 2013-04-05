package com.dubture.getcomposer.core.entities;

public abstract class AbstractUniqueJsonArray<V> extends AbstractJsonArray<V> {

	@Override
	public void add(V value) {
		for (V item : values) {
			if (item.equals(value)) {
				return;
			}
		}
		super.add(value);
	}
}
