package org.zeu.controller.model;

import org.json.JSONException;

public class JoystickMove extends ControllerInput {

	public JoystickMove(int input, float x, float y) {
		super();
		try {
			put("input", input);
			put("x", x);
			put("y", y);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
