package org.zeu.controller.model.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.zeu.controller.Settings;

public class ControllerInput extends JSONObject {

	public ControllerInput() {
		super();
		try {
			put("game_id", Settings.getInstance().gameId);
			put("username", Settings.getInstance().username);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
