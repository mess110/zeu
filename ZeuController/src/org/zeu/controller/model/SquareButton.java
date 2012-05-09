package org.zeu.controller.model;

import org.zeu.controller.Network;
import org.zeu.controller.ZeuControllerActivity;
import org.zeu.controller.model.base.BaseButton;
import org.zeu.controller.model.json.Input;

public class SquareButton extends BaseButton {

	public SquareButton(ZeuControllerActivity zeu, Network net) {
		super(zeu.CAMERA_WIDTH -64, 32, zeu.getSquareTexture(), net, Input.BUTTON_SQUARE);
	}

}
