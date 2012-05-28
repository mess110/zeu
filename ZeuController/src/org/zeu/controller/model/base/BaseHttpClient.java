package org.zeu.controller.model.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.zeu.controller.util.Settings;

public class BaseHttpClient {

	public static String newGame() {
		try {
			return executeHttpPost(Settings.getInstance().runnerUrl
					+ "/new_game");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String getGames() {
		try {
			return executeHttpGet(Settings.getInstance().runnerUrl + "/info");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static void sendCrashReport(String stacktrace) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = Settings.getInstance().serverUrl + "/report";
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("stacktrace", stacktrace));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			httpClient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String executeHttpPost(String url) throws Exception {
		BufferedReader in = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			return sb.toString();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String executeHttpGet(String url) throws Exception {
		BufferedReader in = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			return sb.toString();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
