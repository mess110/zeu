package org.zeu.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

// TODO dry up this code by using constants
public class Persistency {
	private SharedPreferences preferences;
	private static String preferencesString = "org.zeu.pref";

	public Persistency(Context context) {
		preferences = context.getSharedPreferences(preferencesString, 0);

		Settings s = Settings.getInstance();
		s.username = (String) getUsername();
	}

	public void setUrl(String url) {
		Editor editor = preferences.edit();
		editor.putString("url", url);
		editor.commit();
	}
	
	public void setUsername(String username) {
		Editor editor = preferences.edit();
		editor.putString("username", username);
		editor.commit();
	}

	public String getUrl() {
		return preferences.getString("url", "http://192.168.0.109:5000");
	}

	public CharSequence getUsername() {
		return preferences.getString("username", "Player");
	}
}
