package org.zeu.controller.model;

import org.anddev.andengine.input.touch.TouchEvent;
import org.zeu.controller.Network;
import org.zeu.controller.Util;
import org.zeu.controller.ZeuControllerActivity;
import org.zeu.controller.model.base.BaseButton;
import org.zeu.controller.model.json.Input;

public class CircleButton extends BaseButton {

	private ZeuControllerActivity zeu;

	public CircleButton(ZeuControllerActivity zeu, Network net) {
		super(32, 32, zeu.getCircleTexture(), net, Input.BUTTON_CIRCLE);
		this.zeu = zeu;
	}

	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {
			Util.isConnected(zeu, net);
		}
		return true;
	}

}
