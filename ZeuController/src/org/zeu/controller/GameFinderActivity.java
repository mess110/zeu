package org.zeu.controller;

import java.util.ArrayList;

import org.zeu.controller.model.base.HttpClient;
import org.zeu.controller.util.Persistency;
import org.zeu.controller.util.Util;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GameFinderActivity extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Persistency persistency = new Persistency(getApplicationContext());

		String gamesJson = HttpClient.getGames();
		ArrayList<String> games = Util.parseJsonGames(gamesJson);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.game_finder,
				games));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView gameIdTextView = ((TextView) view);
				persistency.setGameId(gameIdTextView.getText().toString());
			}
		});
	}
}