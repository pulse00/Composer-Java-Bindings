package org.getcomposer.packagist;

import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
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

public class DownloadThread extends Thread {

	private HttpGet httpGet;
	private String url;
	private List<DownloadListenerInterface> listeners;
	
	public DownloadThread(Downloader downloader) {
		super("Composer Download Thread");
		url = downloader.getUrl();
		listeners = downloader.getListeners();
	}

	public void abort() {
		synchronized (httpGet) {
			if (httpGet != null) {
				httpGet.abort();
			}
		}
		interrupt();
		
		for (DownloadListenerInterface listener : listeners) {
			listener.aborted();
		}
	}
	
	@Override
	public void run() {
		synchronized (listeners) {
			for (DownloadListenerInterface listener : listeners) {
				listener.setTotalWork(4);
			}
			
			HttpClient client = new DefaultHttpClient();
			
			try {
			
				//if (url.startsWith("https://packagist.org") || url.startsWith("https://getcomposer.org")) {
				if (url.startsWith("https://")) {
					registerSSLContext(client);
				}
				
				httpGet = new HttpGet(url);
				HttpResponse response = null;
				InputStream content = null;				
				response = client.execute(httpGet);
				
				for (DownloadListenerInterface listener : listeners) {
					listener.progressChanged(1);
				}
				
				HttpEntity entity = response.getEntity();
				content = entity.getContent();
			
				for (DownloadListenerInterface listener : listeners) {
					listener.progressChanged(1);
				}

				for (DownloadListenerInterface listener : listeners) {
					listener.dataReceived(content);
				}
				
			} catch (Exception e) {
				for (DownloadListenerInterface listener : listeners) {
					listener.errorOccured(e);
				}
			}
		}
	}

	private void registerSSLContext(HttpClient client) throws Exception {	
		X509TrustManager tm = new ComposerTrustManager();
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(null,  new TrustManager[]{tm}, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = client.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssf));
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
