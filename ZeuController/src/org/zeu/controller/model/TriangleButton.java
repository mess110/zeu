package org.zeu.controller.model;

import org.anddev.andengine.input.touch.TouchEvent;
import org.zeu.controller.ZeuControllerActivity;
import org.zeu.controller.model.base.BaseButton;
import org.zeu.controller.model.json.Input;
import org.zeu.controller.util.Network;

public class TriangleButton extends BaseButton {

	public TriangleButton(ZeuControllerActivity zeu, Network net) {
		super(32 + zeu.getJoystickKnobTexture().getWidth(), 32, zeu
				.getJoystickKnobTexture(), net, Input.BUTTON_TRIANGLE);
	}

	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {
			net.reconnect();
		}
		return true;
	}
}
