package org.zeu.controller;

public class Settings {

	private static Settings instance;
	
	public String username;
	public String url;
	
	private Settings() {
	}
	
	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}
}
