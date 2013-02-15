package org.getcomposer.packages;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class AsyncPackageSearch extends AsyncDownloadClient {
	private int pageLimit = 3;
	protected List<PackageSearchListenerInterface> listeners = new ArrayList<PackageSearchListenerInterface>();
	
	public AsyncPackageSearch(String baseUrl) {
		super(baseUrl, true);
	}
	
	public void addPackageSearchListener(PackageSearchListenerInterface listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public void removePackageSearchListener(PackageSearchListenerInterface listener) {
		listeners.remove(listener);
	}
	
	public int search(String query) {
		downloader.setUrl(createUrl(query));
		downloader.addDownloadListener(new DownloadListenerAdapater() {
			private int counter = 1;
			private boolean aborted = false;
			
			public void dataReceived(InputStream content, String url) {
				if (aborted) {
					return;
				}
				SearchResult result = PackageHelper.getSearchResult(content);
				
				// parse query from url
				String query;
				try {
					query = URLDecoder.decode(url.replaceFirst(".+q=([^?&]+).*", "$1"), "UTF-8");
				
					if (result != null && result.results != null) {
						for (PackageSearchListenerInterface listener : listeners) {
							listener.packagesFound(result.results, query, result);
						}
					}
					
					if (result != null && result.next != null 
							&& result.next.length() > 0 && counter < pageLimit) {
						downloader.setUrl(result.next);
						downloader.download();
						counter++;
					}
				} catch (Exception e) {
					for (PackageSearchListenerInterface listener : listeners) {
						listener.errorOccured(e);
					}
				}
			}
			
			public void aborted() {
				aborted = true;
				for (PackageSearchListenerInterface listener : listeners) {
					listener.aborted();
				}
			}
		});
		
		return downloader.download();
	}

	/**
	 * @return the pageLimit
	 */
	public int getPageLimit() {
		return pageLimit;
	}

	/**
	 * @param pageLimit the pageLimit to set
	 */
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
}
