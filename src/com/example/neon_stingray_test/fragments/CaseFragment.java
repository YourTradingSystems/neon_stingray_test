package com.example.neon_stingray_test.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.neon_stingray_test.R;
import com.example.neon_stingray_test.core.CaseModel;
import com.example.neon_stingray_test.core.ScenarioModel;
import com.example.neon_stingray_test.global.Constants;
import com.example.neon_stingray_test.thread.WebRequest;

/**
 * User: ZOG
 * Date: 28.06.14
 * Time: 20:48
 */
public class CaseFragment extends Fragment implements AdapterView.OnItemClickListener {

	private ImageView ivImage_FC;
	private ListView lvCases_FC;
	private TextView tvQuestion_FC;

	public CaseModel caseModel;

	@Override
	public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
		return _inflater.inflate(R.layout.fragment_case, _container, false);
	}

	@Override
	public final void onActivityCreated(final Bundle _savedInstanceState) {
		super.onActivityCreated(_savedInstanceState);

		findViews();
		setListeners();

		updateData();
	}

	private final void findViews() {
		ivImage_FC = (ImageView) getView().findViewById(R.id.ivImage_FC);
		lvCases_FC = (ListView) getView().findViewById(R.id.lvCases_FC);
		tvQuestion_FC = (TextView) getView().findViewById(R.id.tvQuestion_FC);
	}

	private final void setListeners() {
		lvCases_FC.setOnItemClickListener(this);
	}

	@Override
	public final void onItemClick(final AdapterView<?> _adapterView, final View _view, final int _position, final long _id) {
		final ScenarioModel scenarioModel = (ScenarioModel) _adapterView.getAdapter().getItem(_position);

		final Bundle bundle = new Bundle();
		bundle.putSerializable(Constants.KEY_SCENARIO_MODEL, scenarioModel);

		final WebRequest webRequest = new WebRequest(Constants.API_CASES);
		webRequest.execute(bundle);
	}

	private final void updateData() {
		if (caseModel == null) return;

		tvQuestion_FC.setText(caseModel.text);
		ivImage_FC.setImageBitmap(caseModel.img);

		final ScenarioAdapter scenarioAdapter = new ScenarioAdapter(caseModel.answers);
		lvCases_FC.setAdapter(scenarioAdapter);
	}
}