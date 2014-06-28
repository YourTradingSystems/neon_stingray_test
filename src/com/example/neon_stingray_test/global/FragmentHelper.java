package com.example.neon_stingray_test.global;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * User: ZOG
 * Date: 17.01.14
 * Time: 10:43
 */
public abstract class FragmentHelper {
    private static FragmentManager mFragmentManager;
    private static FragmentTransaction mFragmentTransaction;

    /**
     * save reference to fragment manager from activity
     */
    public static final void initFragmentManager() {
        mFragmentManager = Variables.activity.getFragmentManager();
    }

    /**
     * must call only after init
     * @return reference to FragmentManager
     */
    public static final FragmentManager sharedFragmentManager() {
        return mFragmentManager;
    }

    /**
     * use this for transactions between fragments
     * @return reference to shared variable
     */
    public static final FragmentTransaction sharedFragmentTransaction() {
        return mFragmentTransaction;
    }

    /**
     * begins transaction
     * access to variable through sharedFragmentTransaction() method
     */
    public static final void beginSharedFragmentTransaction() {
        mFragmentTransaction = sharedFragmentManager().beginTransaction();
    }

    public static final void addFragment(final int _rootView, final Fragment _fragment) {
        beginSharedFragmentTransaction();
        final FragmentTransaction transaction = FragmentHelper.sharedFragmentTransaction();

		transaction.add(_rootView, _fragment);
		transaction.addToBackStack(null);
		transaction.commitAllowingStateLoss();
    }

	public static final void replaceFragment(final int _rootView, final Fragment _fragment, final boolean _addToBackStack) {
		beginSharedFragmentTransaction();
		final FragmentTransaction transaction = FragmentHelper.sharedFragmentTransaction();

		transaction.replace(_rootView, _fragment);
		if (_addToBackStack) transaction.addToBackStack(null);
		transaction.commitAllowingStateLoss();
	}

	/**
	 * Used only for new spot check fragments. Deleting all fragment by name when back pressed.
	 * @param _name
	 * @param _rootView
	 * @param _fragment
	 */
	public static final void replaceFragment(final String _name, final int _rootView, final Fragment _fragment) {
		beginSharedFragmentTransaction();
		final FragmentTransaction transaction = FragmentHelper.sharedFragmentTransaction();
		try {
			transaction.replace(_rootView, _fragment);
			transaction.addToBackStack(_name);
			transaction.commit(); //todo: test method commitAllowingStateLoss()
		} catch (final IllegalStateException _e) { _e.printStackTrace(); }
	}

    public static final void deleteAllFragments() {
        try {
            final FragmentManager manager = FragmentHelper.sharedFragmentManager();
            manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (final IllegalStateException _e) { _e.printStackTrace(); }

    }

	public static final void deleteAllFragments(final String _name) {
		try {
			final FragmentManager manager = FragmentHelper.sharedFragmentManager();
			manager.popBackStackImmediate(_name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		} catch (final IllegalStateException _e) { _e.printStackTrace(); }

	}
}