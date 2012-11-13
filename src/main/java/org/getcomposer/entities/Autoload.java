/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.entities;

import org.getcomposer.collection.GenericArray;
import org.getcomposer.collection.Psr0;
import org.getcomposer.serialization.AutoloadSerializer;

import com.google.gson.annotations.SerializedName;

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
	
	@SerializedName("psr-0")
	private Psr0 psr0 = new Psr0();
	
	public String getPsr0Path() {
		if (psr0 == null) {
			return null;
		}
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
	
	
	public static Object getSerializer() {
		return new AutoloadSerializer();
	}
}
