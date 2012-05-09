package org.zeu.controller.model;

import org.zeu.controller.Network;
import org.zeu.controller.ZeuControllerActivity;
import org.zeu.controller.model.base.BaseButton;
import org.zeu.controller.model.json.Input;

public class HexagonButton extends BaseButton {

	public HexagonButton(ZeuControllerActivity zeu, Network net) {
		super(zeu.CAMERA_WIDTH - 128, 32, zeu.getHexagonTexture(), net, Input.BUTTON_HEXAGON);
	}

}
