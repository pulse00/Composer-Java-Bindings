package org.getcomposer.packages;

import org.getcomposer.core.ComposerConstants;

public class AsyncPackagistSearch extends AsyncPackageSearch {

	public AsyncPackagistSearch() {
		super(ComposerConstants.SEARCH_URL);
	}
}
