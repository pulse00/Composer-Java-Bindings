package org.getcomposer.core;

public class Author {
	
	public String name;
	public String email;
	public String homepage;

	/**
	 * We need an empty ctor for gson deserialization.
	 */
	public Author() {
		
	}
	
	public Author(String name) {
		this.name = name;
	}
	
	public String getInitString() {
		return String.format("%s <%s>", name, email);
	}

}
