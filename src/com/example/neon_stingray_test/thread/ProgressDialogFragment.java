package com.example.neon_stingray_test.thread;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.neon_stingray_test.R;
import com.example.neon_stingray_test.global.Variables;

import java.util.HashMap;

/**
 * class for dialog
 * need for changing orientation
 */
final class ProgressDialogFragment extends DialogFragment {
    private static final HashMap<String, ProgressDialogFragment> mDialogs = new HashMap<String, ProgressDialogFragment>();

    protected static final ProgressDialogFragment create() {
        return new ProgressDialogFragment();
    }

	private ProgressDialogFragment() {}

    @Override
    public final Dialog onCreateDialog(final Bundle _savedInstanceState) {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "");
        progressDialog.setCanceledOnTouchOutside(false);
        setCancelable(true);
        return progressDialog;
    }

	@Override
    public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        final View dialogView = _inflater.inflate(R.layout.dialog_loader, null);
        return dialogView;
    }

    @Override
    public final void onCancel(final DialogInterface _dialogInterface) {
        //opportunity to cancel requests to server
    }

    /**
     * start progress dialog for indicating response in specified activity
     */
    protected static final void startProgressDialog(final String _messId) {
        final FragmentManager fragmentManager = Variables.activity.getFragmentManager();
        final ProgressDialogFragment progressDialogFragment = ProgressDialogFragment.create();
        mDialogs.put(_messId, progressDialogFragment);     //put in stack
        progressDialogFragment.show(fragmentManager, "Dialog");
    }

    protected static final void stopProgressDialog(final String _messId) {
        final ProgressDialogFragment progressDialogFragment = mDialogs.get(_messId);

        try {
            if (progressDialogFragment != null) {
                progressDialogFragment.dismiss();
            }
        } catch (final IllegalStateException _e) {
            _e.printStackTrace();
        }
    }

}