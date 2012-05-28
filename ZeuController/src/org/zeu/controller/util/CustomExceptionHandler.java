package org.zeu.controller.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

import org.zeu.controller.model.base.BaseHttpClient;

public class CustomExceptionHandler implements UncaughtExceptionHandler {

	private UncaughtExceptionHandler defaultUEH;

	public CustomExceptionHandler() {
		this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
	}

	public void uncaughtException(Thread t, Throwable e) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		e.printStackTrace(printWriter);
		String stacktrace = result.toString();
		printWriter.close();
		sendToServer(stacktrace);
		defaultUEH.uncaughtException(t, e);
	}

	private void sendToServer(String stacktrace) {
		BaseHttpClient.sendCrashReport(stacktrace);
	}
}