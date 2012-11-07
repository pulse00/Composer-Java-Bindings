package org.getcomposer.entities;

/**
 * Represents a <code>dist</code> entity in a package
 * 
 * @see http://getcomposer.org/doc/05-repositories.md#package-2
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Distribution extends Storage {

	/**
	 * Returns the <code>shasum</code> property.
	 * 
	 * @return the <code>shasum</code> value
	 */
	public String getShaSum() {
		return getAsString("shasum");
	}
	
	/**
	 * Sets the <code>shasum</code> property.
	 * 
	 * @param type the new <code>shasum</code> value
	 * @return this
	 */
	public Distribution setShaSum(String shaSum) {
		set("shasum", shaSum);
		return this;
	}

}
