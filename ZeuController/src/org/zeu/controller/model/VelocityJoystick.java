package org.zeu.controller.model;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.zeu.controller.Network;
import org.zeu.controller.ZeuControllerActivity;

public class VelocityJoystick {
	private AnalogOnScreenControl velocityOnScreenControl;

	public VelocityJoystick(ZeuControllerActivity zeu, final Network net) {
		int x1 = zeu.PADDING;
		int y1 = zeu.CAMERA_HEIGHT - zeu.getJoystickBaseTexture().getHeight()
				- zeu.PADDING;

		velocityOnScreenControl = new AnalogOnScreenControl(x1, y1,
				zeu.getCamera(), zeu.getJoystickBaseTexture(),
				zeu.getJoystickKnobTexture(), 0.1f,
				new IAnalogOnScreenControlListener() {
					@Override
					public void onControlChange(
							final BaseOnScreenControl pBaseOnScreenControl,
							final float pValueX, final float pValueY) {
						net.move(pValueX, pValueY);
					}

					@Override
					public void onControlClick(
							final AnalogOnScreenControl pAnalogOnScreenControl) {
					}
				});
		velocityOnScreenControl.getControlBase().setBlendFunction(
				GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		velocityOnScreenControl.getControlBase().setAlpha(0.5f);
	}

	public AnalogOnScreenControl getVelocityJoystick() {
		return velocityOnScreenControl;
	}
}
