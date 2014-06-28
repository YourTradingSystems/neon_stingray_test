package com.example.neon_stingray_test.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.neon_stingray_test.R;
import com.example.neon_stingray_test.core.ScenarioModel;
import com.example.neon_stingray_test.global.Constants;
import com.example.neon_stingray_test.thread.WebRequest;

import java.util.ArrayList;

/**
 * User: ZOG
 * Date: 28.06.14
 * Time: 20:27
 */
public final class ScenariosFragment extends Fragment implements AdapterView.OnItemClickListener {

	private ListView lvScenarios_FS;

	public ArrayList<ScenarioModel> scenarioModels;

	@Override
	public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
		return _inflater.inflate(R.layout.fragment_scenario, _container, false);
	}

	@Override
	public final void onActivityCreated(final Bundle _savedInstanceState) {
		super.onActivityCreated(_savedInstanceState);

		findViews();
		setListeners();

		setAdapterToList();
	}

	private final void findViews() {
		lvScenarios_FS = (ListView) getView().findViewById(R.id.lvScenarios_FS);
	}

	private final void setListeners() {
		lvScenarios_FS.setOnItemClickListener(this);
	}

	private final void setAdapterToList() {
		if (scenarioModels == null) return;

		final ScenarioAdapter scenarioAdapter = new ScenarioAdapter(scenarioModels);
		lvScenarios_FS.setAdapter(scenarioAdapter);
	}

	@Override
	public final void onItemClick(final AdapterView<?> _adapterView, final View _view, final int _position, final long _id) {
		final ScenarioModel scenarioModel = (ScenarioModel) _adapterView.getAdapter().getItem(_position);

		final Bundle bundle = new Bundle();
		bundle.putSerializable(Constants.KEY_SCENARIO_MODEL, scenarioModel);

		final WebRequest webRequest = new WebRequest(Constants.API_CASES);
		webRequest.execute(bundle);
	}
}
