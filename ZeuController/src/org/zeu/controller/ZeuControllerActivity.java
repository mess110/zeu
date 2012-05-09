package org.zeu.controller;

import io.socket.SocketIO;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.zeu.controller.model.Input;
import org.zeu.controller.model.JoystickMove;

import android.widget.Toast;

public class ZeuControllerActivity extends BaseExample {

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 320;
	private static final int PADDING = 32;

	private Camera mCamera;

	private BitmapTextureAtlas mBitmapTextureAtlas;
	private TextureRegion mSquare, mHexagon, mTriangle, mCircle;

	private BitmapTextureAtlas mOnScreenControlTexture;
	private TextureRegion mOnScreenControlBaseTextureRegion;
	private TextureRegion mOnScreenControlKnobTextureRegion;

	private boolean mPlaceOnScreenControlsAtDifferentVerticalLocations = false;

	private SocketIO socket;
	private Network net;

	public void toast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	@Override
	public Engine onLoadEngine() {
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		RatioResolutionPolicy rrp = new RatioResolutionPolicy(CAMERA_WIDTH,
				CAMERA_HEIGHT);
		EngineOptions eo = new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				rrp, mCamera);
		Engine engine = new Engine(eo);

		try {
			if (MultiTouch.isSupported(this)) {
				engine.setTouchController(new MultiTouchController());
				if (MultiTouch.isSupportedDistinct(this)) {
					toast("MultiTouch detected --> Both controls will work properly!!!");
				} else {
					mPlaceOnScreenControlsAtDifferentVerticalLocations = true;
					toast("MultiTouch detected, but your device has problems distinguishing between fingers.\n\nControls are placed at different vertical locations.");
				}
			} else {
				toast("Sorry your device does NOT support MultiTouch!\n\n(Falling back to SingleTouch.)\n\nControls are placed at different vertical locations.");
			}
		} catch (final MultiTouchException e) {
			toast("Sorry your Android Version does NOT support MultiTouch!\n\n(Falling back to SingleTouch.)\n\nControls are placed at different vertical locations.");
		}
		net = new Network();
		net.connect();

		return engine;
	}

	@Override
	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		mBitmapTextureAtlas = new BitmapTextureAtlas(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mSquare = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mBitmapTextureAtlas, this, "square.png", 0, 0);
		mHexagon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mBitmapTextureAtlas, this, "hexagon.png", 0, 64);
		mTriangle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mBitmapTextureAtlas, this, "triangle.png", 64, 0);
		mCircle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mBitmapTextureAtlas, this, "circle.png", 64, 64);

		mOnScreenControlTexture = new BitmapTextureAtlas(256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mOnScreenControlTexture, this,
						"onscreen_control_base.png", 0, 0);
		mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mOnScreenControlTexture, this,
						"onscreen_control_knob.png", 128, 0);

		mEngine.getTextureManager().loadTextures(mBitmapTextureAtlas,
				mOnScreenControlTexture);
	}

	@Override
	public Scene onLoadScene() {
		mEngine.registerUpdateHandler(new FPSLogger());

		Scene scene = new Scene();
		scene.setBackground(new ColorBackground(0.9f, 0.9f, 0.9f));

		Sprite square = new Sprite(CAMERA_WIDTH - 64, 32, mSquare);
		scene.attachChild(square);
		Sprite triangle = new Sprite(CAMERA_WIDTH - 64, 96, mTriangle);
		scene.attachChild(triangle);
		Sprite hexagon = new Sprite(CAMERA_WIDTH - 128, 32, mHexagon);
		scene.attachChild(hexagon);
		Sprite circle = new Sprite(CAMERA_WIDTH - 128, 96, mCircle);
		scene.attachChild(circle);

		final AnalogOnScreenControl velocityOnScreenControl = getVelocityJoystick();
		scene.setChildScene(velocityOnScreenControl);
		
		final AnalogOnScreenControl rotationOnScreenControl = getRotationJoystick();
		velocityOnScreenControl.setChildScene(rotationOnScreenControl);

		return scene;
	}
	
	private AnalogOnScreenControl getRotationJoystick() {
		final int y1 = CAMERA_HEIGHT
				- this.mOnScreenControlBaseTextureRegion.getHeight() - PADDING;
		final int y2 = (this.mPlaceOnScreenControlsAtDifferentVerticalLocations) ? PADDING
				: y1;
		final int x2 = CAMERA_WIDTH
				- mOnScreenControlBaseTextureRegion.getWidth() - PADDING;
		final AnalogOnScreenControl rotationOnScreenControl = new AnalogOnScreenControl(
				x2, y2, mCamera, mOnScreenControlBaseTextureRegion,
				mOnScreenControlKnobTextureRegion, 0.1f,
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
		return rotationOnScreenControl;
	}

	private AnalogOnScreenControl getVelocityJoystick() {
		final int x1 = PADDING;
		final int y1 = CAMERA_HEIGHT
				- this.mOnScreenControlBaseTextureRegion.getHeight() - PADDING;

		AnalogOnScreenControl velocityOnScreenControl = new AnalogOnScreenControl(
				x1, y1, mCamera, mOnScreenControlBaseTextureRegion,
				mOnScreenControlKnobTextureRegion, 0.1f,
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
						/* Nothing. */
					}
				});
		velocityOnScreenControl.getControlBase().setBlendFunction(
				GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		velocityOnScreenControl.getControlBase().setAlpha(0.5f);
		return velocityOnScreenControl;
	}

	@Override
	public void onLoadComplete() {

	}
}
