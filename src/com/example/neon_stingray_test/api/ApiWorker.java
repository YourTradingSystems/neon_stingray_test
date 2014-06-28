package com.example.neon_stingray_test.api;

import android.os.Bundle;
import org.json.JSONException;

import java.io.IOException;
import java.net.*;
import java.text.ParseException;
import java.util.HashMap;

/**
 * User: ZOG
 * Date: 21.03.14
 * Time: 16:35
 */
public abstract class ApiWorker {

	public static final HashMap<String, Object> apiScenarios(final Bundle _bundle)
			throws IOException, JSONException, ParseException, URISyntaxException {

		final URL url = UrlHelper.buildScenariosUri(_bundle);

		final HttpURLConnection conn = ApiHelper.createScenariosConn(url);
		final HashMap<String, Object> result = ApiHelper.createScenariosResult(conn);

		conn.disconnect();

		return result;
	}

	public static final HashMap<String, Object> apiCases(final Bundle _bundle)
			throws IOException, JSONException, ParseException, URISyntaxException {

		final URL url = UrlHelper.buildCasesUri(_bundle);

		final HttpURLConnection conn = ApiHelper.createCasesConn(url);
		final HashMap<String, Object> result = ApiHelper.createCasesResult(conn);

		conn.disconnect();

		return result;
	}

}