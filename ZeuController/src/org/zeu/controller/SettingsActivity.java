package org.zeu.controller;

import org.zeu.controller.util.Persistency;
import org.zeu.controller.util.Util;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SettingsActivity extends Activity {

	private Button save;
	private EditText url, name, gameId;
	private CheckBox showOnStartup;
	private Persistency pref;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		pref = new Persistency(this);
		save = (Button) findViewById(R.id.go_button);

		url = (EditText) findViewById(R.id.editText1);
		url.setText(pref.getUrl());

		name = (EditText) findViewById(R.id.editText2);
		name.setText(pref.getUsername());

		gameId = (EditText) findViewById(R.id.editText3);
		gameId.setText(pref.getGameId());

		showOnStartup = (CheckBox) findViewById(R.id.checkBox1);
		showOnStartup.setChecked(pref.isShowSettingsAtStartup());

		save.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				pref.setUsername(name.getText().toString());
				pref.setUrl(url.getText().toString());
				pref.setGameId(gameId.getText().toString());
				pref.setSettingsAtStartup(showOnStartup.isChecked());
				Util.toast(getApplicationContext(), "saved");
			}
		});
	}
}