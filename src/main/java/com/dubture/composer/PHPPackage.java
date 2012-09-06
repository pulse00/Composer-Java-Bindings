/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.dubture.composer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 */
public class PHPPackage
{
    public String name;
    public String type;
    public String description;
    public String homepage;
    public String url;
    public String fullPath;
    public Map<String, String> require;
    public Map<String, String> requireDev;
    public Autoload autoload;
    public String targetDir;
    public String version;
    public String versionNormalized;
    public License license;
    public String[] keywords;
    public Map<String, PHPPackage> versions;
    
    public String toString()
    {
        return name;
    }


    public static PHPPackage fromJson(File input) throws FileNotFoundException 
    {
        Gson gson = getBuilder();
        InputStream stream = new FileInputStream(input);
        InputStreamReader reader = new InputStreamReader(stream);
        PHPPackage pHPPackage = gson.fromJson(reader, PHPPackage.class);
        pHPPackage.fullPath = input.getAbsolutePath();
        
        return pHPPackage;

    }
    
    public static PHPPackage fromPackagist(File input) throws FileNotFoundException
    {
        Gson gson = getBuilder();
        InputStream stream = new FileInputStream(input);
        InputStreamReader reader = new InputStreamReader(stream);
        PackagistPackage packagistPackage = gson.fromJson(reader, PackagistPackage.class);
        
        return packagistPackage.phpPackage;
    }
    
    public static Gson getBuilder() 
    {
        return new GsonBuilder()
        	.registerTypeAdapter(License.class, new LicenseDeserializer())
            .setFieldNamingStrategy(new ComposerFieldNamingStrategy())
            .create();
    }
    

    public String getDefaultVersion()
    {
        return versions.keySet().iterator().next();
    }
    
    public String getPackageName(String version) throws Exception
    {
        if (!versions.containsKey(version)) {
            throw new Exception("Invalid version " + version + " for package " + name);
        }
        
        return String.format("%s:%s", name, version);
    }
    
    public class PackagistPackage {

    	public PHPPackage phpPackage;
    	
    }
}
