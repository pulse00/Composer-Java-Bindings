package org.getcomposer.core;

public class Support extends ObservableModel {

	private String email;
	private String issues;
	private String forum;
	private String wiki;
	private String irc;
	private String source;
	
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
	 * @return the issues
	 */
	public String getIssues() {
		return issues;
	}
	
	/**
	 * @param issues the issues to set
	 */
	public void setIssues(String issues) {
		firePropertyChange("issues", this.issues, this.issues = issues);
	}
	
	/**
	 * @return the forum
	 */
	public String getForum() {
		return forum;
	}
	
	/**
	 * @param forum the forum to set
	 */
	public void setForum(String forum) {
		firePropertyChange("forum", this.forum, this.forum = forum);
	}
	
	/**
	 * @return the wiki
	 */
	public String getWiki() {
		return wiki;
	}
	
	/**
	 * @param wiki the wiki to set
	 */
	public void setWiki(String wiki) {
		firePropertyChange("wiki", this.wiki, this.wiki = wiki);
	}
	
	/**
	 * @return the irc
	 */
	public String getIrc() {
		return irc;
	}
	
	/**
	 * @param irc the irc to set
	 */
	public void setIrc(String irc) {
		firePropertyChange("irc", this.irc, this.irc = irc);
	}
	
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		firePropertyChange("source", this.source, this.source = source);
	}
}
