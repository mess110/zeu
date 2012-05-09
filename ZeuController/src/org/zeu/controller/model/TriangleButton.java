package org.zeu.controller.model;

import org.zeu.controller.Network;
import org.zeu.controller.ZeuControllerActivity;
import org.zeu.controller.model.base.BaseButton;
import org.zeu.controller.model.json.Input;

public class TriangleButton extends BaseButton {

	public TriangleButton(ZeuControllerActivity zeu, Network net) {
		super(zeu.CAMERA_WIDTH -64, 96, zeu.getTriangleTexture(), net, Input.BUTTON_TRIANGLE);
	}

}
