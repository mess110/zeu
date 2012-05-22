package org.zeu.controller.model;

import org.anddev.andengine.input.touch.TouchEvent;
import org.zeu.controller.ZeuControllerActivity;
import org.zeu.controller.model.base.BaseButton;
import org.zeu.controller.model.json.Input;
import org.zeu.controller.util.Network;

public class ConnectButton extends BaseButton {

	public ConnectButton(ZeuControllerActivity zeu, Network net) {
		super(zeu.CAMERA_WIDTH / 2 - zeu.getJoystickKnobTexture().getWidth()
				/ 2, zeu.CAMERA_HEIGHT / 2
				- zeu.getJoystickKnobTexture().getHeight() / 2, zeu
				.getConnectBaseTexture(), net, Input.BUTTON_CIRCLE);
	}

	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {
			net.reconnect();
		}
		return true;
	}
}
