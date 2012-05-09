package org.zeu.controller.model;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.zeu.controller.Network;

import android.util.Log;

public class Button extends Sprite {

	private Network net;
	private int kind;

	public Button(float pX, float pY, TextureRegion pTextureRegion, Network net, int kind) {
		super(pX, pY, pTextureRegion);
		this.net = net;
		this.kind = kind;
	}

	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		Log.d("asd", "fuc");
		net.press(kind);
		return true;
	}

}
