package org.getcomposer.entities;

/**
 * Represents a support section of a composer package.
 * 
 * @see http://getcomposer.org/doc/04-schema.md#support
 * @author Thomas Gossmann <gos.si>
 */
public class Support extends GenericEntity implements Cloneable {

	/**
	 * Returns the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
//		System.out.println("Supprt.getEmail " + get("email").));
		return getAsString("email");
	}
	
	/**
	 * Sets the email.
	 * 
	 * @param email the email to set
	 * @return this
	 */
	public Support setEmail(String email) {
		set("email", email);
		return this;
	}
	
	/**
	 * Returns the issues.
	 * 
	 * @return the issues
	 */
	public String getIssues() {
		return getAsString("issues");
	}
	
	/**
	 * Sets the issues.
	 * 
	 * @param issues the issues to set
	 * @return this
	 */
	public Support setIssues(String issues) {
		set("issues", issues);
		return this;
	}
	
	/**
	 * Returns the forum.
	 * 
	 * @return the forum
	 */
	public String getForum() {
		return getAsString("forum");
	}
	
	/**
	 * Sets the forum.
	 * 
	 * @param forum the forum to set
	 * @return this
	 */
	public Support setForum(String forum) {
		set("forum", forum);
		return this;
	}
	
	/**
	 * Returns the wiki.
	 * 
	 * @return the wiki
	 */
	public String getWiki() {
		return getAsString("wiki");
	}
	
	/**
	 * Sets the wiki.
	 * 
	 * @param wiki the wiki to set
	 * @return this
	 */
	public Support setWiki(String wiki) {
		set("wiki", wiki);
		return this;
	}
	
	/**
	 * Returns the irc.
	 * 
	 * @return the irc
	 */
	public String getIrc() {
		return getAsString("irc");
	}
	
	/**
	 * Sets the irc.
	 * 
	 * @param irc the irc to set
	 * @return this
	 */
	public Support setIrc(String irc) {
		set("irc", irc);
		return this;
	}
	
	/**
	 * Returns the source.
	 * 
	 * @return the source
	 */
	public String getSource() {
		return getAsString("source");
	}
	
	/**
	 * Sets the source.
	 * 
	 * @param source the source to set
	 * @return this
	 */
	public Support setSource(String source) {
		set("source", source);
		return this;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Support clone() {
		return (Support)super.clone();
	}
}
