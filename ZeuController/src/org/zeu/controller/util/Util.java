package org.zeu.controller.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.zeu.controller.ZeuControllerActivity;

import android.content.Context;
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
}
