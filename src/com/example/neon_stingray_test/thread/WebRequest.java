package com.example.neon_stingray_test.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import com.example.neon_stingray_test.api.ApiWorker;
import com.example.neon_stingray_test.api.ErrorHelper;
import com.example.neon_stingray_test.api.ResponseWorker;
import com.example.neon_stingray_test.core.CaseModel;
import com.example.neon_stingray_test.core.ScenarioModel;
import com.example.neon_stingray_test.global.Constants;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * class for sending request to server
 */
public final class WebRequest extends AsyncTask<Bundle, Void, HashMap<String, Object>> {

    private String mApi;

	public WebRequest(final String _api) {
		mApi = _api;
	}

    @Override
    protected final HashMap<String, Object> doInBackground(final Bundle... _bundles) {

		HashMap<String, Object> result = new HashMap<String, Object>();

        if (isCancelled()) return null;

		try {

			if (mApi.equals(Constants.API_SCENARIOS)) {
					result = ApiWorker.apiScenarios(_bundles[0]);
			} else if (mApi.equals(Constants.API_CASES)) {
				result = ApiWorker.apiCases(_bundles[0]);
			}

		} catch (final IOException _e) {
			_e.printStackTrace();
			final Bundle bundle = ErrorHelper.createErrorBundle(_e.toString());
			result.put(Constants.ERROR, bundle);

		} catch (final JSONException _e) {
			_e.printStackTrace();
			final Bundle bundle = ErrorHelper.createErrorBundle(_e.toString());
			result.put(Constants.ERROR, bundle);

		} catch (final ParseException _e) {
			_e.printStackTrace();
			final Bundle bundle = ErrorHelper.createErrorBundle(_e.toString());
			result.put(Constants.ERROR, bundle);

		} catch (final Throwable _e) {
			_e.printStackTrace();
			final Bundle bundle = ErrorHelper.createErrorBundle(_e.toString());
			result.put(Constants.ERROR, bundle);

		} finally {
			return result;
		}

    }

    @Override
    public final void onPreExecute() {
//        if (!Network.isInternetConnectionAvailable(Variables.activity)) return;

        ProgressDialogFragment.startProgressDialog(mApi);
    }

    @Override
    public final void onPostExecute(final HashMap<String, Object> _result) {

		if (_result == null) return;

		if (_result.containsKey(Constants.API_SCENARIOS)) {
			final ArrayList<ScenarioModel> scenarios = (ArrayList<ScenarioModel>) _result.get(Constants.API_SCENARIOS);
			ResponseWorker.responseApiScenarios(scenarios);

		} else if (_result.containsKey(Constants.API_CASES)) {
			final CaseModel caseModel = (CaseModel) _result.get(Constants.API_CASES);
			ResponseWorker.responseApiCases(caseModel);

		} else if (_result.containsKey(Constants.ERROR)) {
			final Bundle bundle = (Bundle) _result.get(Constants.ERROR);
			ResponseWorker.respApiError(bundle);
		}

        ProgressDialogFragment.stopProgressDialog(mApi);
    }
}