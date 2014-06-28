package com.example.neon_stingray_test.api;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.example.neon_stingray_test.global.Constants;
import com.example.neon_stingray_test.global.Variables;

/**
 * User: ZOG
 * Date: 26.03.14
 * Time: 15:51
 */
public abstract class ErrorHelper {

	public static final Bundle createErrorBundle(final int _statusCode) {
		final String errorDescription = getErrorDescription(_statusCode);

		final Bundle bundle = new Bundle();
		bundle.putString(Constants.KEY_ERROR_DESCRIPTION, errorDescription);
		return bundle;
	}

	public static final Bundle createErrorBundle(final String _description) {
		final Bundle bundle = new Bundle();
		bundle.putString(Constants.KEY_ERROR_DESCRIPTION, _description);
		return bundle;
	}

	private static final String getErrorDescription(final int _errorCode) {
		switch (_errorCode) {
			default: return "Unexpected error: " + _errorCode;
		}
	}

	public static final void showErrorDialog(final Bundle _bundle) {
		final String errorDescription = _bundle.getString(Constants.KEY_ERROR_DESCRIPTION);

		new AlertDialog.Builder(Variables.activity)
				.setTitle("Error")
				.setMessage(errorDescription)
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public final void onClick(final DialogInterface _dialog, final int _which) {
						// continue with delete
					}
				})
				.setIcon(android.R.drawable.ic_dialog_alert)
				.show();
	}
}