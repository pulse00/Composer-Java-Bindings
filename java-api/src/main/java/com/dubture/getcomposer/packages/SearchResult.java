package com.dubture.getcomposer.packages;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.dubture.getcomposer.core.MinimalPackage;

public class SearchResult {
	public List<MinimalPackage> results;
	public String next;
	public String total;
	
	public SearchResult(Object obj) {
		fromJson(obj);
	}
	
	@SuppressWarnings("rawtypes")
	public void fromJson(Object obj) {
		if (obj instanceof LinkedHashMap) {
			LinkedHashMap json = (LinkedHashMap) obj;
			
			next = (String)json.get("next");
			total = json.get("total").toString();
			results = new LinkedList<MinimalPackage>();
			Object r = json.get("results");
			
			if (r instanceof LinkedList) {
				for (Object p : (LinkedList) r) {
					results.add(new MinimalPackage(p));
				}
			}
		}
	}
}
