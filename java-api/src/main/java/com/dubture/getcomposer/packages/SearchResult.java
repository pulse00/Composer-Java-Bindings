package com.dubture.getcomposer.packages;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dubture.getcomposer.core.MinimalPackage;

public class SearchResult {
	public List<MinimalPackage> results;
	public String next;
	public String total;
	
	public SearchResult(Object obj) {
		fromJson(obj);
	}
	
	public void fromJson(Object obj) {
		if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			
			next = (String)json.get("next");
			total = json.get("total").toString();
			results = new ArrayList<MinimalPackage>();
			Object r = json.get("results");
			
			if (r instanceof JSONArray) {
				
				for (Object p : (JSONArray) r) {
					results.add(new MinimalPackage(p));
				}
			}
		}
	}
}
