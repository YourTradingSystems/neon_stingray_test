package com.example.neon_stingray_test.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.neon_stingray_test.R;
import com.example.neon_stingray_test.core.ScenarioModel;
import com.example.neon_stingray_test.global.Variables;

import java.util.ArrayList;

/**
 * User: ZOG
 * Date: 28.06.14
 * Time: 20:28
 */
public final class ScenarioAdapter extends BaseAdapter {

	private LayoutInflater mLayoutInflater;
	private ArrayList<ScenarioModel> mScenarioModels;

	public ScenarioAdapter(final ArrayList<ScenarioModel> _scenarioModels) {
		mScenarioModels = _scenarioModels;
		mLayoutInflater = (LayoutInflater) Variables.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public final int getCount() {
		return mScenarioModels.size();
	}

	@Override
	public final Object getItem(final int _position) {
		return mScenarioModels.get(_position);
	}

	@Override
	public final long getItemId(final int _position) {
		return _position;
	}

	@Override
	public final View getView(final int _position, View _convertView, final ViewGroup _parent) {
		if (_convertView == null) {
			_convertView = mLayoutInflater.inflate(R.layout.list_item_scenario, _parent, false);
		}

		((TextView) _convertView.findViewById(R.id.tvQuestion_LIS)).setText(mScenarioModels.get(_position).text);

		return _convertView;
	}
}
