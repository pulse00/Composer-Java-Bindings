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
	 */
	public void setEmail(String email) {
		set("email", email);
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
	 */
	public void setIssues(String issues) {
		set("issues", issues);
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
	 */
	public void setForum(String forum) {
		set("forum", forum);
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
	 */
	public void setWiki(String wiki) {
		set("wiki", wiki);
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
	 */
	public void setIrc(String irc) {
		set("irc", irc);
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
	 */
	public void setSource(String source) {
		set("source", source);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Support clone() {
		return (Support)super.clone();
	}
}
