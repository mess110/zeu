package org.zeu.controller;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import java.net.MalformedURLException;

import org.json.JSONObject;

public class Network implements IOCallback {

	private SocketIO socket;
	private static Network instance;

	public static Network getInstance() {
		if (instance == null) {
			instance = new Network();
		}
		return instance;
	}

	private Network() {
		socket = new SocketIO();
	}

	public void connect() throws MalformedURLException {
		socket.connect("http://127.0.0.1:5000/", this);
		socket.emit("viewport_register");
	}

	public SocketIO getSocket() {
		return socket;
	}

	@Override
	public void onMessage(JSONObject json, IOAcknowledge ack) {
	}

	@Override
	public void onMessage(String data, IOAcknowledge ack) {
		System.out.println("Server said: " + data);
	}

	@Override
	public void onError(SocketIOException socketIOException) {
		System.out.println("an Error occured");
		socketIOException.printStackTrace();
	}

	@Override
	public void onDisconnect() {
		System.out.println("Connection terminated.");
	}

	@Override
	public void onConnect() {
		System.out.println("Connection established");
	}

	@Override
	public void on(String event, IOAcknowledge ack, Object... args) {
		System.out.println("EVENT: " + event);
		System.out.println("DATA: " + (JSONObject) args[0]);
	}
}