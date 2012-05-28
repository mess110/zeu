package org.zeu.controller.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

// Settings and Persistency are quite ugly. fix it
public class Persistency {
	private SharedPreferences preferences;
	private Settings settings;
	private static String preferencesString = "org.zeu.pref";
	private static final String RUNNER_URL = "runnerUrl";
	private static final String SERVER_URL = "serverUrl";
	private static final String GAME_ID = "gameId";
	private static final String USERNAME = "username";
	private static final String SETTINGS_AT_STARTUP = "showSettingsAtStartup";

	public Persistency(Context context) {
		preferences = context.getSharedPreferences(preferencesString, 0);

		settings = Settings.getInstance();
		settings.username = getUsername();
		settings.runnerUrl = getRunnerUrl();
		settings.serverUrl = getServerUrl();
		settings.gameId = getGameId();
		settings.showSettingsAtStartup = isShowSettingsAtStartup();
	}

	public String getServerUrl() {
		return preferences.getString(SERVER_URL, "http://192.168.0.109:3000");
	}

	public void setServerUrl(String serverUrl) {
		Editor editor = preferences.edit();
		editor.putString(SERVER_URL, serverUrl);
		editor.commit();
		settings.serverUrl = serverUrl;
	}

	public String getRunnerUrl() {
		return preferences.getString(RUNNER_URL, "http://192.168.0.109:5000");
	}

	public void setRunnerUrl(String runnerUrl) {
		Editor editor = preferences.edit();
		editor.putString(RUNNER_URL, runnerUrl);
		editor.commit();
		settings.runnerUrl = runnerUrl;
	}

	public String getUsername() {
		return preferences.getString(USERNAME, "Player");
	}

	public void setUsername(String username) {
		Editor editor = preferences.edit();
		editor.putString(USERNAME, username);
		editor.commit();
		settings.username = username;
	}

	public String getGameId() {
		return preferences.getString(GAME_ID, "0");
	}

	public void setGameId(String gameId) {
		Editor editor = preferences.edit();
		editor.putString(GAME_ID, gameId);
		editor.commit();
		settings.gameId = gameId;
	}

	public boolean isShowSettingsAtStartup() {
		return preferences.getBoolean(SETTINGS_AT_STARTUP, true);
	}

	public void setSettingsAtStartup(boolean showSettingsAtStartup) {
		Editor editor = preferences.edit();
		editor.putBoolean(SETTINGS_AT_STARTUP, showSettingsAtStartup);
		editor.commit();
		settings.showSettingsAtStartup = showSettingsAtStartup;
	}
}
