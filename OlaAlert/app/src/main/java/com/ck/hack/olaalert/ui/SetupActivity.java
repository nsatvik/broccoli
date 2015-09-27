package com.ck.hack.olaalert.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.ck.hack.olaalert.R;
import com.ck.hack.olaalert.app.OlaAlertApp;
import com.ck.hack.olaalert.app.UserInfo;


public class SetupActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private static final String LOGTAG = SetupActivity.class.getSimpleName();
    private SetupStep mCurrentStep;
    private FragmentManager mFragMan;
    private UserInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOGTAG, "onCreate+");

        setContentView(R.layout.activity_initial_setup);

        mFragMan = getSupportFragmentManager();
        mFragMan.addOnBackStackChangedListener(this);
        mUserInfo = ((OlaAlertApp) getApplication()).getDataManager().getUserInfo();
        Fragment f;

        mCurrentStep = SetupStep.USER_LOGIN;
        f = SetupLoginFragment.getInstance();

        mFragMan.beginTransaction()
                .replace(R.id.fragment, f)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        Log.v(LOGTAG, "Back pressed. Step = " + mCurrentStep);

        if (mCurrentStep == SetupStep.USER_LOGIN) {
            finish();
        }
    }

    public UserInfo getEnteredUserInfo() {
        return mUserInfo;
    }


    public void moveToNextStep() {
        if (mCurrentStep == SetupStep.USER_LOGIN) {
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    @Override
    public void onBackStackChanged() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        if (mCurrentStep == SetupStep.ENTER_USER_INFO) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        } else if (mCurrentStep == SetupStep.VERIFY_USER_INFO || mCurrentStep == SetupStep.USER_LOGIN) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Log.v(LOGTAG, "Back in action bar pressed. Step = " + mCurrentStep);
                if (mCurrentStep.equals(SetupStep.ENTER_USER_INFO)) {
                    Log.e(LOGTAG, "SetupUserInfoFragment should have menu items at all!");
                    finish();
                } else {
                    onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public enum SetupStep {
        ENTER_USER_INFO,
        VERIFY_USER_INFO,
        USER_LOGIN
    }
}
