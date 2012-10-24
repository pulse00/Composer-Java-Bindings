package org.getcomposer;

import org.getcomposer.serialization.SupportSerializer;

/**
 * Represents a support section of a composer package.
 * 
 * @see http://getcomposer.org/doc/04-schema.md#support
 * @author Thomas Gossmann <gos.si>
 */
public class Support extends ObservableModel implements Cloneable {

	private String email;
	private String issues;
	private String forum;
	private String wiki;
	private String irc;
	private String source;
	
	/**
	 * Returns the email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email
	 * 
	 * @param email the email to set
	 * @return this
	 */
	public Support setEmail(String email) {
		firePropertyChange("email", this.email, this.email = email);
		return this;
	}
	
	/**
	 * Returns the issues
	 * 
	 * @return the issues
	 */
	public String getIssues() {
		return issues;
	}
	
	/**
	 * Sets the issues
	 * 
	 * @param issues the issues to set
	 * @return this
	 */
	public Support setIssues(String issues) {
		firePropertyChange("issues", this.issues, this.issues = issues);
		return this;
	}
	
	/**
	 * Returns the forum
	 * 
	 * @return the forum
	 */
	public String getForum() {
		return forum;
	}
	
	/**
	 * Sets the forum
	 * 
	 * @param forum the forum to set
	 * @return this
	 */
	public Support setForum(String forum) {
		firePropertyChange("forum", this.forum, this.forum = forum);
		return this;
	}
	
	/**
	 * Returns the wiki
	 * 
	 * @return the wiki
	 */
	public String getWiki() {
		return wiki;
	}
	
	/**
	 * Sets the wiki
	 * 
	 * @param wiki the wiki to set
	 * @return this
	 */
	public Support setWiki(String wiki) {
		firePropertyChange("wiki", this.wiki, this.wiki = wiki);
		return this;
	}
	
	/**
	 * Returns the irc
	 * 
	 * @return the irc
	 */
	public String getIrc() {
		return irc;
	}
	
	/**
	 * Sets the irc
	 * 
	 * @param irc the irc to set
	 * @return this
	 */
	public Support setIrc(String irc) {
		firePropertyChange("irc", this.irc, this.irc = irc);
		return this;
	}
	
	/**
	 * Returns the source
	 * 
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * Sets the source
	 * 
	 * @param source the source to set
	 * @return this
	 */
	public Support setSource(String source) {
		firePropertyChange("source", this.source, this.source = source);
		return this;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Support clone() {
		try {
			return (Support)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	public static Object getSerializer() {
		return new SupportSerializer();
	}
}
