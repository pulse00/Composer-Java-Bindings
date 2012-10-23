package org.getcomposer.packagist;

public interface ProgressListenerInterface {

	void progressChanged(int worked);

	void setTotalWork(int total);

	void worked();

}
