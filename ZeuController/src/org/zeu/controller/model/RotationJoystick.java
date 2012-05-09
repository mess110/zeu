package org.zeu.controller.model;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.zeu.controller.Network;
import org.zeu.controller.ZeuControllerActivity;

public class RotationJoystick {
	private AnalogOnScreenControl rotationOnScreenControl;

	public RotationJoystick(ZeuControllerActivity zeu, final Network net) {
		int y1 = zeu.CAMERA_HEIGHT - zeu.getJoystickBaseTexture().getHeight()
				- zeu.PADDING;
		int y2 = (zeu.placeJoysticksAtDifferentVerticalLocations()) ? zeu.PADDING
				: y1;
		int x2 = zeu.CAMERA_WIDTH - zeu.getJoystickBaseTexture().getWidth()
				- zeu.PADDING;
		rotationOnScreenControl = new AnalogOnScreenControl(x2, y2,
				zeu.getCamera(), zeu.getJoystickBaseTexture(),
				zeu.getJoystickKnobTexture(), 0.1f,
				new IAnalogOnScreenControlListener() {
					@Override
					public void onControlChange(
							final BaseOnScreenControl pBaseOnScreenControl,
							final float pValueX, final float pValueY) {
						net.rotate(pValueX, pValueY);
					}

					@Override
					public void onControlClick(
							final AnalogOnScreenControl pAnalogOnScreenControl) {
						/* Nothing. */
					}
				});
		rotationOnScreenControl.getControlBase().setBlendFunction(
				GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		rotationOnScreenControl.getControlBase().setAlpha(0.5f);
	}

	public AnalogOnScreenControl getRotationJoystick() {
		return rotationOnScreenControl;
	}
}
