/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.core.objects;

import java.util.Arrays;
import java.util.LinkedList;

import org.getcomposer.core.annotation.Name;
import org.getcomposer.core.collection.JsonArray;
import org.getcomposer.core.collection.Psr0;
import org.json.simple.JSONObject;


/**
 * Represents the autoload entity of a composer package.
 * 
 * @see http://getcomposer.org/doc/04-schema.md#autoload
 * @author Robert Gruendler <r.gruendler@gmail.com>
 * @author Thomas Gossmann <gos.si>
 */
public class Autoload extends JsonObject {
	
	private JsonArray classmap = new JsonArray();
	private JsonArray files = new JsonArray();
	
	@Name("psr-0")
	private Psr0 psr0 = new Psr0();
	
	public Autoload() {
		listen();
	}
	
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			
			parseField(json, "psr-0");
			parseField(json, "classmap");
			parseField(json, "files");
		}
	}
	
	@Override
	public Object prepareJson(LinkedList<String> fields) {
		if (classmap.size() == 0 && files.size() == 0 && psr0.size() == 0) {
			return null;
		}
		String[] order = new String[]{"psr-0", "classmap", "files"};
		fields.addAll(Arrays.asList(order));
		return super.prepareJson(fields);
	}
	
	public String getPsr0Path() {
		if (psr0.size() > 0) {
			return ((Namespace)psr0.iterator().next()).getFirst();
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
	
	public JsonArray getClassMap() {
		return classmap;
	}
	
	public JsonArray getFiles() {
		return files;
	}

	public void setPsr0(Psr0 psr0) {
		this.psr0 = psr0;
	}

	public Namespace getNamespace() {
		return (Namespace)psr0.iterator().next();
	}
}
