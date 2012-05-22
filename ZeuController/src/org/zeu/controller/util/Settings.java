package org.zeu.controller.util;

public class Settings {

	private static Settings instance;

	public String username;
	public String url;
	public String gameId;
	public boolean showSettingsAtStartup;

	private Settings() {
	}

	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}
}
