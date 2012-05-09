package org.zeu.controller;

import android.content.Context;
import android.widget.Toast;

public class Util {

	public static void toast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
}
