/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.getcomposer.serialization.LicenseSerializer;

/**
 * Represents the license property of a composer package
 * 
 * @see http://getcomposer.org/doc/04-schema.md#license
 * @author Thomas Gossmann <gos.si>
 */
public class License implements Iterable<String> {

	private List<String> licenses;

	public License() {
		licenses = new ArrayList<String>();
	}
	
	/**
	 * Adds a license
	 * 
	 * @param license
	 */
	public void add(String license) {
		licenses.add(license);
	}
	
	/**
	 * Returns a license at the given zero-related index
	 * 
	 * @param index
	 * @return the license
	 */
	public String get(int index) {
		return licenses.get(index);
	}
	
	/**
	 * Returns the amount of licenses
	 * @return
	 */
	public int size() {
		return licenses.size();
	}
	
	/**
	 * Removes a license
	 */
	public void remove(String license) {
		licenses.remove(license);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<String> iterator() {
		return licenses.iterator();
	}
	
	public static Object getSerializer() {
		return new LicenseSerializer();
	}
}
