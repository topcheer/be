package com.brightedu.server.util;

public abstract class ConfigurationChangeListener {
	
	private long lastModified;
	
	private String file;
	
	public abstract void configurationChanged();

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
