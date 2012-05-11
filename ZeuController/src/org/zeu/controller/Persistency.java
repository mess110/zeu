package org.zeu.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

// TODO dry up this code by using constants
// Settings and Persistency are quite ugly. fix it
public class Persistency {
	private SharedPreferences preferences;
	private Settings settings;
	private static String preferencesString = "org.zeu.pref";

	public Persistency(Context context) {
		preferences = context.getSharedPreferences(preferencesString, 0);

		settings = Settings.getInstance();
		settings.username = getUsername();
		settings.url = getUrl();
		settings.gameId = getGameId();
	}

	public void setUrl(String url) {
		Editor editor = preferences.edit();
		editor.putString("url", url);
		editor.commit();
		settings.url = url;
	}
	
	public void setUsername(String username) {
		Editor editor = preferences.edit();
		editor.putString("username", username);
		editor.commit();
		settings.username = username;
	}
	
	public void setGameId(String gameId) {
		Editor editor = preferences.edit();
		editor.putString("gameId", gameId);
		editor.commit();
		settings.gameId = gameId;
	}

	public String getUrl() {
		return preferences.getString("url", "http://192.168.0.109:5000");
	}

	public String getUsername() {
		return preferences.getString("username", "Player");
	}

	public String getGameId() {
		return preferences.getString("gameId", "1");
	}
}
