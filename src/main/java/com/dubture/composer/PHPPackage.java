/*
 * This file is part of the PHPPackage Eclipse Plugin.
 *
 * (c) Robert Gruendler <r.gruendler@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.dubture.composer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 *
 */
public class PHPPackage
{
    private String name;
    private String type;
    private String description;
    private String homepage;
    private String url;
    private String fullPath;
    private Map<String, String> require;
    private Map<String, String> requireDev;
    private Autoload autoload;
    private String targetDir;
    
    public String version;
    public String versionNormalized;
    
    public License license;
    
    public String[] keywords;
    
    public Map<String, PHPPackage> versions;
    
    public String toString()
    {
        return getName();
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getHomepage()
    {
        return homepage;
    }

    public void setHomepage(String homepage)
    {
        this.homepage = homepage;
    }

    public Map<String, String> getRequire()
    {
        return require;
    }

    public void setRequire(Map<String, String> require)
    {
        this.require = require;
    }

    public Autoload getAutoload()
    {
        return autoload;
    }

    public void setAutoload(Autoload autoload)
    {
        this.autoload = autoload;
    }
    

    public String getName()
    {
        return name;
    }
    
    public static PHPPackage fromJson(File input) throws FileNotFoundException 
    {
        Gson gson = getBuilder();
        InputStream stream = new FileInputStream(input);
        InputStreamReader reader = new InputStreamReader(stream);
        PHPPackage pHPPackage = gson.fromJson(reader, PHPPackage.class);
        pHPPackage.setFullPath(input.getAbsolutePath());
        
        return pHPPackage;

    }
    
    protected static Gson getBuilder() 
    {
        return new GsonBuilder()
        	.registerTypeAdapter(License.class, new LicenseDeserializer())
            .setFieldNamingStrategy(new ComposerFieldNamingStrategy())
            .create();
    }
    
    @Override
    public int hashCode()
    {
        return getName().hashCode();
    }

    public String getTargetDir()
    {
        return targetDir;
    }

    public void setTargetDir(String targetDir)
    {
        this.targetDir = targetDir;
    }
    
    public String getFullPath()
    {
        return fullPath;
    }

    public void setFullPath(String fullPath)
    {
        this.fullPath = fullPath;
    }

    public Map<String, String> getRequireDev()
    {
        return requireDev;
    }

    public void setRequireDev(Map<String, String> requireDev)
    {
        this.requireDev = requireDev;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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
}
