package org.zeu.controller.model.base;

import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Menu;
import android.view.MenuItem;

public abstract class BaseExample extends BaseGameActivity {
	private final int ID_MENU_EXIT = 0;
	private final int ID_MENU_SETTINGS = 1;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_MENU_SETTINGS, Menu.NONE, "settings");
		menu.add(Menu.NONE, ID_MENU_EXIT, Menu.NONE, "exit");

		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ID_MENU_EXIT:
			this.finish();
			break;
		case ID_MENU_SETTINGS:
			//Intent myIntent = new Intent(this, SettingsActivity.class);
			//startActivity(myIntent);
			break;
		default:
			break;
		}
		return false;
	}
}
