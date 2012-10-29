package org.getcomposer.entities;


/**
 * Represents a person entry in a composer package that is used in authors 
 * and maintainers
 * 
 * @see http://getcomposer.org/doc/04-schema.md#authors
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Person extends GenericEntity implements Cloneable {

	/**
	 * Creates an empty person
	 */
	public Person() {
		
	}
	
	/**
	 * Creates a person with a name
	 * @param name
	 */
	public Person(String name) {
		set("name", name);
	}

	/**
	 * Returns a string that is passed to composer's init command
	 * 
	 * @return
	 */
	public String getInitString() {
		return String.format("%s <%s>", get("name"), get("email"));
	}

	/**
	 * Returns the person's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return getAsString("name");
	}

	/**
	 * Sets the person's name
	 * 
	 * @param name the name to set
	 * @return this
	 */
	public Person setName(String name) {
		set("name", name);
		return this;
	}

	/**
	 * Returns the perons's email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return getAsString("email");
	}

	/**
	 * Sets the person's email
	 * 
	 * @param email the email to set
	 * @return this
	 */
	public Person setEmail(String email) {
		set("email", email);
		return this;
	}

	/**
	 * Returns the person's homepage
	 * 
	 * @return the homepage
	 */
	public String getHomepage() {
		return getAsString("homepage");
	}

	/**
	 * Sets the person's homepage
	 * 
	 * @param homepage the homepage to set
	 * @return this
	 */
	public Person setHomepage(String homepage) {
		set("homepage", homepage);
		return this;
	}

	/**
	 * Returns the person's role
	 * 
	 * @return the role
	 */
	public String getRole() {
		return getAsString("role");
	}

	/**
	 * Sets the person's role
	 * 
	 * @param role the role to set
	 * @return this
	 */
	public Person setRole(String role) {
		set("role", role);
		return this;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Person clone() {
		return (Person)super.clone();
	}
}
