package org.getcomposer.core.objects;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;


/**
 * Represents a person entry in a composer package that is used in authors 
 * and maintainers
 * 
 * @see http://getcomposer.org/doc/04-schema.md#authors
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Person extends JsonObject implements Cloneable {

	/**
	 * Creates an empty person
	 */
	public Person() {
		listen();
	}
	
	public Person(Object json) {
		super();
		fromJson(json);
		listen();
	}
	
	public Person(String json) {
		super();
		fromJson(json);
		listen();
	}
	
	public Person(File file) throws IOException {
		super();
		fromJson(file);
		listen();
	}
	
	public Person(Reader reader) throws IOException {
		super();
		fromJson(reader);
		listen();
	}
	
	@Override
	public Object prepareJson(LinkedList<String> fields) {
		String[] order = new String[]{"name", "email", "homepage", "role"};
		fields.addAll(Arrays.asList(order));
		return super.prepareJson(fields);
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
		Person clone = new Person();
		cloneProperties(clone);
		return clone;
	}
	
	/*
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person person = (Person) obj;
			
			return getName() != null && getName().equals(person.getName())
					&& getEmail() != null && getEmail().equals(person.getEmail())
					&& getHomepage() != null && getHomepage().equals(person.getHomepage())
					&& getRole() != null && getRole().equals(person.getRole());	
		}
		
		return false;
	}
	*/
}
