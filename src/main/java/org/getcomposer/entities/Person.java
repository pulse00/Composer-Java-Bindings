package org.getcomposer.entities;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONValue;


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
	
	public Person(Object json) {
		super();
		parse(json);
	}
	
	public Person(String json) {
		super();
		parse(JSONValue.parse(json));
	}
	
	public Person(File file) throws IOException {
		super();
		load(file);
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
	 */
	public void setName(String name) {
		set("name", name);
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
	 */
	public void setEmail(String email) {
		set("email", email);
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
	 */
	public void setHomepage(String homepage) {
		set("homepage", homepage);
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
	public void setRole(String role) {
		set("role", role);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Person clone() {
		return (Person)super.clone();
	}
}
