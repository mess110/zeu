package org.zeu.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {
	private SharedPreferences preferences;
	private static String preferencesString = "org.zeu.pref";

	public Preferences(Context context) {
		preferences = context.getSharedPreferences(preferencesString, 0);
	}

	public void setUrl(String url) {
		Editor editor = preferences.edit();
		editor.putString("url", url);
		editor.commit();
	}

	public String getUrl() {
		return preferences.getString("url", "http://192.168.0.109:5000");
	}
}
