package org.zeu.controller;

import java.net.MalformedURLException;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import org.json.JSONObject;
import org.zeu.controller.model.json.ButtonPress;
import org.zeu.controller.model.json.Input;
import org.zeu.controller.model.json.JoystickMove;

import android.util.Log;

public class Network implements IOCallback {
	
	private SocketIO socket;

	public Network() {
		socket = new SocketIO();
	}
	
	public boolean isConnected() {
		return socket.isConnected();
	}
	
	public void connect(String url) {
		try {
			socket.connect(url, this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.d("connection", "can't connect");
			e.printStackTrace();
		}
	}
	
	public void move(float x, float y) {
		socket.emit("controller_action", new JoystickMove(
				Input.JOYSTICK_VELOCITY, x, y));
	}
	
	public void rotate(float x, float y) {
		socket.emit("controller_action", new JoystickMove(
				Input.JOYSTICK_ROTATION, x, y));
	}

	public void press(int kind) {
		socket.emit("controller_action", new ButtonPress(kind));
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