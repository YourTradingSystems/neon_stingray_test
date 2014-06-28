package com.example.neon_stingray_test.api;

import com.example.neon_stingray_test.core.CaseModel;
import com.example.neon_stingray_test.core.ScenarioModel;
import com.example.neon_stingray_test.global.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * User: ZOG
 * Date: 26.03.14
 * Time: 15:54
 */
abstract class Parser {

	protected static final ArrayList<ScenarioModel> parseScenarios(final String _response)
			throws JSONException, ParseException {

		final ArrayList<ScenarioModel> scenarios = new ArrayList<ScenarioModel>();

		final JSONObject jsonObj = new JSONObject(_response);
		final JSONArray jaScenarios = jsonObj.getJSONArray(Constants.KEY_SCENARIOS);

		for (int i = 0; i < jaScenarios.length(); i++) {
			final JSONObject scenario = jaScenarios.getJSONObject(i);
			final ScenarioModel scenariosModel = parseScenarioModel(scenario);
			scenarios.add(scenariosModel);
		}

		return scenarios;
	}

	/**
	 * Parse ScenarioModel. Helper method.
	 * @param scenario json obj with scenario data.
	 * @return new ScenarioModel.
	 */
	private static final ScenarioModel parseScenarioModel(final JSONObject scenario) throws JSONException {

		final String text = scenario.getString(Constants.KEY_TEXT);
		final String id = scenario.getString(Constants.KEY_ID);
		final String caseId = scenario.getString(Constants.KEY_CASE_ID);

		final ScenarioModel scenariosModel = new ScenarioModel();
		scenariosModel.text = text;
		scenariosModel.id = id;
		scenariosModel.caseId = caseId;

		return scenariosModel;
	}

	/**
	 * Parse response from cases request. Uses parseScenarioModel() for parsing answers.
	 * @param _response string from response.
	 * @return
	 * @throws JSONException
	 */
	protected static final CaseModel parseCases(final String _response) throws JSONException {

		final JSONObject jsonObj = new JSONObject(_response);
		final JSONObject jCase = jsonObj.getJSONObject(Constants.KEY_CASE);

		//parsing main data
		final String text = jCase.getString(Constants.KEY_TEXT);
		final String imgUrl = jCase.getString(Constants.KEY_IMAGE);
		final String id = jCase.getString(Constants.KEY_ID);

		//parsing answers
		final ArrayList<ScenarioModel> answers = new ArrayList<ScenarioModel>();

		final JSONArray jaAnswers = jCase.optJSONArray(Constants.KEY_ANSWERS);
		if (jaAnswers != null) {
			for (int i = 0; i < jaAnswers.length(); i++) {
				final ScenarioModel answer = parseScenarioModel(jaAnswers.getJSONObject(i));
				answers.add(answer);
			}
		}

		//add parsed data to model
		final CaseModel caseModel = new CaseModel();
		caseModel.text = text;
		caseModel.imgUrl = imgUrl;
		caseModel.id = id;
		caseModel.answers = answers;

		return caseModel;
	}

}