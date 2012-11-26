/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.entities;

import java.util.Arrays;
import java.util.LinkedList;

import org.getcomposer.annotation.Name;
import org.getcomposer.collection.GenericArray;
import org.getcomposer.collection.Psr0;
import org.json.simple.JSONObject;


/**
 * Represents the autoload entity of a composer package.
 * 
 * @see http://getcomposer.org/doc/04-schema.md#autoload
 * @author Robert Gruendler <r.gruendler@gmail.com>
 * @author Thomas Gossmann <gos.si>
 */
public class Autoload extends GenericEntity {
	
	private GenericArray classmap = new GenericArray();
	private GenericArray files = new GenericArray();
	
	@Name("psr-0")
	private Psr0 psr0 = new Psr0();
	
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			
			if (json.containsKey("psr-0")) {
				psr0.load(json.get("psr-0"));
			}
			
			if (json.containsKey("classmap")) {
				classmap.load(json.get("classmap"));
			}
			
			if (json.containsKey("files")) {
				files.load(json.get("files"));
			}
		}
	}
	
	@Override
	public Object prepareJson(LinkedList<String> fields) {
		String[] order = new String[]{"psr-0", "classmap", "files"};
		return super.prepareJson(new LinkedList<String>(Arrays.asList(order)));
	}
	
	public String getPsr0Path() {
		if (psr0.size() > 0) {
			return ((Namespace)psr0.iterator().next()).get();
		}
		return null;
	}

	public boolean hasPsr0() {
		return psr0 != null;
	}
	
	public boolean hasClassMap() {
		return classmap != null && classmap.size() > 0;
	}
	
	public boolean hasFiles() {
		return files != null && files.size() > 0;
	}
	
	public Psr0 getPsr0() {
		return psr0;
	}

//	public Autoload setPsr0(Psr0 psr0) {
//		firePropertyChange("psr-0", this.psr0, this.psr0 = psr0);
//		return this;
//	}
	
	public GenericArray getClassMap() {
		return classmap;
	}
	
	public GenericArray getFiles() {
		return files;
	}
}
