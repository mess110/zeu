package org.zeu.controller.model.json;

import org.json.JSONException;
import org.zeu.controller.model.base.BaseInput;

public class JoystickMove extends BaseInput {

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
