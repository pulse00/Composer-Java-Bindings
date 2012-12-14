package org.getcomposer.packagist;

import java.util.ArrayList;
import java.util.List;

import org.getcomposer.ComposerPackage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchResult {
	public List<ComposerPackage> results;
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
			results = new ArrayList<ComposerPackage>();
			Object r = json.get("results");
			
			if (r instanceof JSONArray) {
				
				for (Object p : (JSONArray) r) {
					results.add(new ComposerPackage(p));
				}
			}
		}
	}
}
