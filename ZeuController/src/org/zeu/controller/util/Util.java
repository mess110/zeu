package org.zeu.controller.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.zeu.controller.GameFinderActivity;
import org.zeu.controller.NewGameActivity;
import org.zeu.controller.SettingsActivity;
import org.zeu.controller.ZeuControllerActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Util {

	public static void toast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	public static void isConnected(ZeuControllerActivity zeu, Network net) {
		toast(zeu, "connected: " + net.isConnected());
	}

	public static long timestamp() {
		Date date = new Date();
		return date.getTime();
	}

	public static ArrayList<String> parseJsonGames(String gamesJson) {
		ArrayList<String> results = new ArrayList<String>();
		try {
			JSONObject jsonObject = new JSONObject(gamesJson);
			JSONObject games = jsonObject.getJSONObject("games");
			Iterator<?> iterator = games.keys();
			while (iterator.hasNext()) {
				results.add((String) iterator.next());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	public static String parseGameId(String gameJson) {
		try {
			JSONObject jsonObject = new JSONObject(gameJson);
			return jsonObject.getString("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static void launchGameFinder(Context context) {
		Intent settingsIntent = new Intent(context, GameFinderActivity.class);
		context.startActivity(settingsIntent);
	}

	public static void launchSettings(Context context) {
		Intent settingsIntent = new Intent(context, SettingsActivity.class);
		context.startActivity(settingsIntent);
	}

	public static void log(String string) {
		Log.d("zeu", string);
	}

	// TODO better function name
	public static void launchNewGame(Context context) {
		Intent newGameIntent = new Intent(context, NewGameActivity.class);
		context.startActivity(newGameIntent);
	}
}
