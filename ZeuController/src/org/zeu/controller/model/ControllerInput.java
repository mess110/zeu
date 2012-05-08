package org.zeu.controller.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ControllerInput extends JSONObject {

	public ControllerInput() {
		super();
		try {
			put("id", "12345");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
