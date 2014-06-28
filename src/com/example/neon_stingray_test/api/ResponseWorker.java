package com.example.neon_stingray_test.api;

import android.os.Bundle;
import com.example.neon_stingray_test.R;
import com.example.neon_stingray_test.core.CaseModel;
import com.example.neon_stingray_test.core.ScenarioModel;
import com.example.neon_stingray_test.fragments.CaseFragment;
import com.example.neon_stingray_test.fragments.ScenariosFragment;
import com.example.neon_stingray_test.global.FragmentHelper;
import com.example.neon_stingray_test.global.FragmentPool;

import java.util.ArrayList;

/**
 * User: ZOG
 * Date: 04.04.14
 * Time: 16:40
 */
public abstract class ResponseWorker {

	public static final void respApiError(final Bundle _bundle) {
		ErrorHelper.showErrorDialog(_bundle);
	}

	public static final void responseApiScenarios(ArrayList<ScenarioModel> _scenarioModels) {
		final ScenariosFragment scenariosFragment = new ScenariosFragment();
		scenariosFragment.scenarioModels = _scenarioModels;

		FragmentHelper.addFragment(R.id.rlRoot_SM, scenariosFragment);
	}

	public static final void responseApiCases(final CaseModel _caseModel) {
		final CaseFragment caseFragment = new CaseFragment();
		caseFragment.caseModel = _caseModel;

		FragmentHelper.replaceFragment(R.id.rlRoot_SM, caseFragment, true);
	}

}