package org.zeu.controller.model;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.zeu.controller.util.Network;

public class ConnectionUpdateHandler implements IUpdateHandler {

	private float elapsed = 0;
	private float RESET_TIME = 1f;
	private Network net;
	private ConnectButton connectButton;

	public ConnectionUpdateHandler(Network net, ConnectButton connectButton) {
		this.net = net;
		this.connectButton = connectButton;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		elapsed += pSecondsElapsed;
		if (elapsed > RESET_TIME) {
			elapsed = 0;
			boolean shouldShould = !net.isConnected();
			connectButton.setVisible(shouldShould);
		}

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
