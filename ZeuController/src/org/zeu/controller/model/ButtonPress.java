package org.zeu.controller.model;

import org.json.JSONException;

public class ButtonPress extends ControllerInput{

	public ButtonPress(int button) {
		super();
		try {
			put("button", button);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
