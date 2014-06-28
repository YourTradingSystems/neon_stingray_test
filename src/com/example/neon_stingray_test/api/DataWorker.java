package com.example.neon_stingray_test.api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * User: ZOG
 * Date: 01.04.14
 * Time: 11:36
 */
abstract class DataWorker {

	protected static final void writeDataToConn(final HttpURLConnection _conn, final JSONObject _json) throws IOException {
		final DataOutputStream printout = new DataOutputStream(_conn.getOutputStream());
		printout.writeBytes(_json.toString());
		printout.flush();
		printout.close();
	}

	protected static final String readDataFromConn(final HttpURLConnection _conn) throws IOException {
		final BufferedReader r = new BufferedReader(new InputStreamReader(_conn.getInputStream()));
		final StringBuilder total = new StringBuilder();
		String line;
		while ((line = r.readLine()) != null) {
			total.append(line);
		}
		return total.toString();
	}
}
