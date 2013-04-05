package com.dubture.getcomposer.packages;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.dubture.getcomposer.core.RepositoryPackage;

public class AsyncPackageDownloader extends AsyncDownloadClient {
	
	private List<PackageListenerInterface> pkgListeners = new ArrayList<PackageListenerInterface>();
	
	public AsyncPackageDownloader(String baseUrl) {
		super(baseUrl);
	}
	
	public void addPackageListener(PackageListenerInterface listener) {
		if (!pkgListeners.contains(listener)) {
			pkgListeners.add(listener);
		}
	}
	
	public void removePackageListener(PackageListenerInterface listener) {
		pkgListeners.remove(listener);
	}
	
	protected void notifyPackageListener(Object obj) {
		for (PackageListenerInterface listener : pkgListeners) {
			if (obj instanceof RepositoryPackage) {
				listener.packageLoaded((RepositoryPackage)obj);
			} else if (obj instanceof Exception) {
				listener.errorOccured((Exception) obj);
			}
		}
	}
	
	public int loadPackage(String packageName) {
		downloader.setUrl(createUrl(packageName));
		if (downloadListener == null) {
			downloadListener = new DownloadListenerAdapater() {
				@Override
				public void dataReceived(InputStream content, String url) {
					try {
						notifyPackageListener(PackageHelper.getPackage(content));
					} catch (Exception e) {
						notifyPackageListener(e);
					}
				}
				
				@Override
				public void errorOccured(Exception e) {
					notifyPackageListener(e);
				}
			};
			downloader.addDownloadListener(downloadListener);
		}
		
		return downloader.download();
	}
}
