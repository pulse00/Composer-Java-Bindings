/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.core.packagist;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.getcomposer.core.ProgressListener;


/**
 * 
 * @author Robert Gruendler <r.gruendler@gmail.com>
 * 
 */
public class Downloader {
	protected String url;

	protected List<ProgressListener> listeners;

	private HttpGet httpGet;

	public Downloader(String url) {
		this.setUrl(url);
		listeners = new ArrayList<ProgressListener>();
	}

	public void addProgressListener(ProgressListener listener) {

		listeners.add(listener);

	}

	public void removeProgressListener(ProgressListener listener) {

		listeners.remove(listener);
	}

	public InputStream downloadResource() throws IOException {

		for (ProgressListener listener : listeners) {
			listener.setTotalWork(4);
		}

		HttpClient client = new DefaultHttpClient();
		
		String url = getUrl();
		
		if (url.startsWith("https://packagist.org") || url.startsWith("https://getcomposer.org")) {
			registerSSLContext(client);
		}
		
		httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);

		for (ProgressListener listener : listeners) {
			listener.progressChanged(1);
		}

		try {
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();

			for (ProgressListener listener : listeners) {
				listener.progressChanged(1);
			}

			for (ProgressListener listener : listeners) {
				listener.worked();
			}

			return content;

		} catch (Exception e) {
			//TODO: log
		} finally {
			for (ProgressListener listener : listeners) {
				listener.worked();
			}
		}

		throw new IOException("Error downloading resource");
	}

	private void registerSSLContext(HttpClient client) {
		
		try {
			X509TrustManager tm = new ComposerTrustManager();
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null,  new TrustManager[]{tm}, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ClientConnectionManager ccm = client.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void abort() {
		
		if (httpGet != null) {
			httpGet.abort();
		}
	}
	
	private class ComposerTrustManager implements X509TrustManager {
		
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
		
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			
		}
		
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			
		}
	}
}
