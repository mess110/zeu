package org.zeu.controller;

import java.util.ArrayList;

import org.zeu.controller.model.base.HttpClient;
import org.zeu.controller.util.Persistency;
import org.zeu.controller.util.Util;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GameFinderActivity extends ListActivity {

	private Persistency persistency;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		persistency = new Persistency(getApplicationContext());
		searchForGames();
	}

	public void searchForGames() {
		String gamesJson = HttpClient.getGames();
		ArrayList<String> games = new ArrayList<String>();
		if (!gamesJson.equals("")) {
			games = Util.parseJsonGames(gamesJson);
		} else {
			Util.toast(this, "couldn't connect to server");
		}
		games.add(0, "refresh");

		setListAdapter(new ArrayAdapter<String>(this, R.layout.game_finder,
				games));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					searchForGames();
				} else {
					TextView gameIdTextView = ((TextView) view);
					persistency.setGameId(gameIdTextView.getText().toString());
					finish();
				}
			}
		});
	}
}