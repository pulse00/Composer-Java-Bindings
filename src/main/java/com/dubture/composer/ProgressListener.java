package com.dubture.composer;

public interface ProgressListener {
	
	void progressChanged(int worked);
	void setTotalWork(int total);
	void worked();

}
