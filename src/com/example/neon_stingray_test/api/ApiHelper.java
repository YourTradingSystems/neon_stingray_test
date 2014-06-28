package com.example.neon_stingray_test.api;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.example.neon_stingray_test.core.CaseModel;
import com.example.neon_stingray_test.core.ScenarioModel;
import com.example.neon_stingray_test.global.Constants;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: ZOG
 * Date: 26.03.14
 * Time: 15:32
 */
abstract class ApiHelper {

	/**
	 * Creates blank for HttpUrlConnection with timeout.
	 * (Add here new general params if needed).
	 * @param _url resource url.
	 * @return new instance of HttpUrlConnection.
	 * @throws java.io.IOException
	 */
	private static final HttpURLConnection createDefaultConn(final URL _url) throws IOException {

		final HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
		conn.setConnectTimeout(Constants.CONN_TIMEOUT);

		return conn;
	}


	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CONNECTIONS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	protected static final HttpURLConnection createScenariosConn(final URL _url) throws IOException {

		final HttpURLConnection conn = createDefaultConn(_url);
//		conn.setDoInput(true);
//		conn.setDoOutput(true);
		conn.setRequestMethod(HttpGet.METHOD_NAME);
//		conn.setRequestProperty("Accept", "application/json");
//		conn.setRequestProperty("Content-type", "application/json");
		return conn;
	}

	protected static final HttpURLConnection createCasesConn(final URL _url) throws IOException {

		final HttpURLConnection conn = createDefaultConn(_url);
//		conn.setDoInput(true);
//		conn.setDoOutput(true);
		conn.setRequestMethod(HttpGet.METHOD_NAME);
//		conn.setRequestProperty("Accept", "application/json");
//		conn.setRequestProperty("Content-type", "application/json");
		return conn;
	}


	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RESULTS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	protected static final HashMap<String, Object> createScenariosResult(final HttpURLConnection _conn)
			throws IOException, JSONException, ParseException {

		final HashMap<String, Object> result = new HashMap<String, Object>();
		final String responseStr;

		final int statusCode = _conn.getResponseCode();
		switch (statusCode) {
			case 200:
				responseStr = DataWorker.readDataFromConn(_conn);
				final ArrayList<ScenarioModel> scenarios = Parser.parseScenarios(responseStr);
				result.put(Constants.API_SCENARIOS, scenarios);
				break;

			default:
				final Bundle bundle = ErrorHelper.createErrorBundle(statusCode);
				result.put(Constants.ERROR, bundle);
				break;
		}

		return result;
	}

	protected static final HashMap<String, Object> createCasesResult(final HttpURLConnection _conn)
			throws IOException, JSONException, ParseException {

		final HashMap<String, Object> result = new HashMap<String, Object>();
		final String responseStr;

		final int statusCode = _conn.getResponseCode();
		switch (statusCode) {
			case 200:
				responseStr = DataWorker.readDataFromConn(_conn);
				final CaseModel caseModel = Parser.parseCases(responseStr);

				//when img not exists or some other problems
				try {
					caseModel.img = BitmapFactory.decodeStream(new URL(caseModel.imgUrl).openStream());
				} catch (final IOException _e) {
					_e.printStackTrace();
				}

				result.put(Constants.API_CASES, caseModel);
				break;

			default:
				final Bundle bundle = ErrorHelper.createErrorBundle(statusCode);
				result.put(Constants.ERROR, bundle);
				break;
		}

		return result;
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}