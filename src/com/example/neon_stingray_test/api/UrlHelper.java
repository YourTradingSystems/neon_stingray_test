package com.example.neon_stingray_test.api;

import android.os.Bundle;
import com.example.neon_stingray_test.core.ScenarioModel;
import com.example.neon_stingray_test.global.Constants;

import java.net.*;

/**
 * User: ZOG
 * Date: 31.03.14
 * Time: 17:33
 */
abstract class UrlHelper {

	/**
	 * Builds scenarios url. Bundle used if need add some param in future.
	 * @param _bundle bundle with params
	 * @return built url.
	 * @throws MalformedURLException
	 */
	protected static final URL buildScenariosUri(final Bundle _bundle) throws MalformedURLException, URISyntaxException {
		//get data from bundle
		//

//		final List<NameValuePair> urlParams = new LinkedList<NameValuePair>();
//		urlParams.add(new BasicNameValuePair(key, val));

//		final String paramStr = URLEncodedUtils.format(urlParams, "UTF-8");

		final String addr = Constants.SERVER_URL + Constants.URL_DIVIDER + Constants.SEG_SCENARIOS;

		final URL url = new URI("http", "//" + addr, null).toURL();
		return url;
	}

	protected static final URL buildCasesUri(final Bundle _bundle) throws MalformedURLException, URISyntaxException {
		//get data from bundle
		final ScenarioModel scenarioModel = (ScenarioModel) _bundle.getSerializable(Constants.KEY_SCENARIO_MODEL);

		final String addr = Constants.SERVER_URL + Constants.URL_DIVIDER
				+ Constants.SEG_CASES + Constants.URL_DIVIDER
				+ scenarioModel.caseId;

		final URL url = new URI("http", "//" + addr, null).toURL();
		return url;
	}


}