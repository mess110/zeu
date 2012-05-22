package org.zeu.controller.model;

import org.zeu.controller.ZeuControllerActivity;
import org.zeu.controller.model.base.BaseButton;
import org.zeu.controller.model.json.Input;
import org.zeu.controller.util.Network;

public class SquareButton extends BaseButton {

	public SquareButton(ZeuControllerActivity zeu, Network net) {
		super(zeu.CAMERA_WIDTH - 32 - zeu.getJoystickKnobTexture().getWidth()
				* 2, 32, zeu.getJoystickKnobTexture(), net, Input.BUTTON_SQUARE);
	}

}
