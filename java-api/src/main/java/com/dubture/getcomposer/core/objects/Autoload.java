/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.dubture.getcomposer.core.objects;

import com.dubture.getcomposer.core.annotation.Name;
import com.dubture.getcomposer.core.collection.JsonArray;
import com.dubture.getcomposer.core.collection.Psr0;


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
		super();
		listen();
	}
	
	public boolean hasPsr0() {
		return psr0 != null && psr0.size() > 0;
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
	
	@Override
	public int size() {
		return super.size() + classmap.size() + files.size() + psr0.size();
	}
}
