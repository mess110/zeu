package org.zeu.controller;

import org.zeu.controller.model.base.BaseHttpClient;
import org.zeu.controller.util.Persistency;
import org.zeu.controller.util.Util;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class NewGameActivity extends Activity {

	private Button newGame;
	private Persistency pref;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.new_game);

		pref = new Persistency(this);

		newGame = (Button) findViewById(R.id.create_button);
		newGame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String gameJson = BaseHttpClient.newGame();
				String gameId = Util.parseGameId(gameJson);
				pref.setGameId(gameId);
				finish();
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {

		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		super.onResume();
		//gameId.setText(pref.getGameId());
	}
}