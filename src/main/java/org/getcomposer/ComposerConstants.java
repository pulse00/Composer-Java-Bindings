/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer;

public class ComposerConstants {
	
	public static final String SEARCH_URL = "https://packagist.org/search.json?q=%s";
	public static final String PACKAGE_URL = "https://packagist.org/packages/%s.json";
	public static final String PHAR_URL = "https://getcomposer.org/composer.phar";
	public static final String STABLE = "stable";
	public static final String RC = "RC";
	public static final String BETA = "beta";
	public static final String ALPHA = "alpha";
	public static final String DEV = "dev";
	public static final String[] STABILITIES = {STABLE, RC, BETA, ALPHA, DEV};
	public static final String[] CONSTRAINTS = {"~", ">", ">=", "<", "<=", "!="};
	public static final String[] LICENSES = {"Apache-2.0","BSD-2-Clause","BSD-3-Clause","BSD-4-Clause","GPL-2.0","GPL-2.0+","GPL-3.0","GPL-3.0+","LGPL-2.1","LGPL-2.1+","LGPL-3.0","LGPL-3.0+","MIT"};
}
