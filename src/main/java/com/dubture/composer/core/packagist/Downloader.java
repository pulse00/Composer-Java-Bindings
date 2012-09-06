/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.dubture.composer.core.packagist;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Downloader
{
    protected String url;

    public Downloader(String url)
    {
        this.setUrl(url);
    }

    public InputStream downloadResource() throws IOException
    {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(getUrl());
        HttpResponse response = client.execute(get);
        
        try {
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			return content;
			
		} catch (Exception e) {
			//TODO: log exception
			if (get != null) {
				get.releaseConnection();
			}
		} 
        
        throw new IOException("Error downloading resource");
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
