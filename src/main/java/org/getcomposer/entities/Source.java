package org.getcomposer.entities;

import org.getcomposer.internal.serialization.ClientEntitySerializer;

/**
 * Represents a <code>source</code> entity in a package
 * 
 * @see http://getcomposer.org/doc/05-repositories.md#package-2
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Source extends Storage {

	public static Object getSerializer() {
		return new ClientEntitySerializer<Source>();
	}
}
