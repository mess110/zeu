package org.zeu.controller.util;

import java.net.MalformedURLException;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import org.json.JSONObject;
import org.zeu.controller.model.json.ButtonRelease;
import org.zeu.controller.model.json.Input;
import org.zeu.controller.model.json.JoystickMove;
import org.zeu.controller.model.json.Register;

import android.util.Log;

public class Network implements IOCallback {

	private SocketIO socket;

	public Network() {
		initSocket();
	}

	private void initSocket() {
		socket = new SocketIO();
	}

	public boolean isConnected() {
		return socket.isConnected();
	}

	public void connect(String url) {
		try {
			socket.connect(url, this);
			socket.emit("controller_register", new Register());
		} catch (MalformedURLException e) {
			Log.d("connection", "can't connect");
			e.printStackTrace();
		}
	}

	public void disconnect() {
		socket.disconnect();
	}

	public void reconnect() {
		if (isConnected()) {
			disconnect();
		}
		initSocket();
		connect(Settings.getInstance().url);
	}

	public void move(float x, float y) {
		//if (x == 0 && y == 0) {
		//	return;
		//}
		socket.emit("controller_action", new JoystickMove(
				Input.JOYSTICK_VELOCITY, x, y));
	}

	public void rotate(float x, float y) {
		//if (x == 0 && y == 0) {
		//	return;
		//}
		socket.emit("controller_action", new JoystickMove(
				Input.JOYSTICK_ROTATION, x, y));
	}

	public void press(int kind) {
		socket.emit("controller_action", new ButtonRelease(kind));
	}

	@Override
	public void on(String arg0, IOAcknowledge arg1, Object... arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(SocketIOException arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(String arg0, IOAcknowledge arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(JSONObject arg0, IOAcknowledge arg1) {
		// TODO Auto-generated method stub

	}
}