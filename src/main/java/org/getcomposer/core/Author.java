package org.getcomposer.core;

public class Author extends ObservableModel implements Cloneable {
	
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		firePropertyChange("name", this.name, this.name = name);
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		firePropertyChange("email", this.email, this.email = email);
	}

	/**
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}

	/**
	 * @param homepage the homepage to set
	 */
	public void setHomepage(String homepage) {
		firePropertyChange("homepage", this.homepage, this.homepage = homepage);
	}
	
	public Author clone() {
		try {
			return (Author)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
