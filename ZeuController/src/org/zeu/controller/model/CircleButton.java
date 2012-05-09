package org.zeu.controller.model;

import org.zeu.controller.Network;
import org.zeu.controller.ZeuControllerActivity;
import org.zeu.controller.model.base.BaseButton;
import org.zeu.controller.model.json.Input;

public class CircleButton extends BaseButton {

	public CircleButton(ZeuControllerActivity zeu, Network net) {
		super(zeu.CAMERA_WIDTH - 128, 96, zeu.getCircleTexture(), net, Input.BUTTON_CIRCLE);
	}

}
