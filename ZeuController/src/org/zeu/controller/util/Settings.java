package org.zeu.controller.util;

public class Settings {

	private static Settings instance;

	public String gameId;
	public String username;
	public String runnerUrl;
	public String serverUrl;
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
