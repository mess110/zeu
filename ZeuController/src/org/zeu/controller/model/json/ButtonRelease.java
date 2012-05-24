package org.zeu.controller.model.json;

import org.json.JSONException;
import org.zeu.controller.model.base.BaseInput;

public class ButtonRelease extends BaseInput {

	public ButtonRelease(int button) {
		super();
		try {
			put("input", button);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
