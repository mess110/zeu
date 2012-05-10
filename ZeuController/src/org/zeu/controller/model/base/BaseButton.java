package org.zeu.controller.model.base;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.zeu.controller.Network;

public class BaseButton extends Sprite {

	protected Network net;
	private int kind;

	public BaseButton(float pX, float pY, TextureRegion pTextureRegion,
			Network net, int kind) {
		super(pX, pY, pTextureRegion);
		this.net = net;
		this.kind = kind;
	}

	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {
			net.press(kind);
		}
		return true;
	}

}
