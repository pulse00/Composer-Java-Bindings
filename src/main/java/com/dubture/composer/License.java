package com.dubture.composer;

public class License {
	
	private String[] names;
	
	public License() {
		names = new String[]{};
	}
	
	public License(String[] names) {
		this.names = names;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}
}
