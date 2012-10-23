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

public class License implements Iterable<String> {

	private List<String> licenses;

	public License() {
		licenses = new ArrayList<String>();
	}
	
	public void add(String license) {
		licenses.add(license);
	}
	
	public String get(int index) {
		return licenses.get(index);
	}
	
	public int size() {
		return licenses.size();
	}
	
	public void remove(String license) {
		licenses.remove(license);
	}

	public Iterator<String> iterator() {
		return licenses.iterator();
	}
}
