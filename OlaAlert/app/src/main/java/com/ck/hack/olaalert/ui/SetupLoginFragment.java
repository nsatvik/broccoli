package com.ck.hack.olaalert.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ck.hack.olaalert.R;
import com.ck.hack.olaalert.app.DataManager;
import com.ck.hack.olaalert.app.OlaAlertApp;
import com.ck.hack.olaalert.app.UserInfo;
import com.ck.hack.olaalert.utils.Utils;

/**
 * Created by Satvik on 16/08/15.
 */
public class SetupLoginFragment extends Fragment {

    private static final String LOGTAG = SetupLoginFragment.class.getSimpleName();


    private EditText mName;
    private EditText mEmail;
    private ProgressBar mProgBar;

    private DataManager mDataMan;

    public SetupLoginFragment() {
        // NO OP
    }

    public static SetupLoginFragment getInstance() {
        return new SetupLoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity host = getActivity();
        OlaAlertApp app = (OlaAlertApp) host.getApplication();

        mDataMan = app.getDataManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(LOGTAG, "onCreateView+");
        View rootView = inflater.inflate(
                R.layout.fragment_complete_user_profile, container, false);

        final SetupActivity hostActivity = (SetupActivity) getActivity();
        ActionBar actionBar = hostActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Ola Alert Setup");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        mProgBar = (ProgressBar) rootView.findViewById(R.id.prog_bar);


        mEmail = (EditText) rootView.findViewById(R.id.email);
        mName = (EditText) rootView.findViewById(R.id.name);


        rootView.findViewById(R.id.done_buttn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNextClick();
            }
        });
        return rootView;


    }

    private void handleNextClick() {
        UserInfo ui = mDataMan.getUserInfo();
        ui.setEmail(mEmail.getText().toString());
        ui.setName(mName.getText().toString());

        SetupActivity host = ((SetupActivity) getActivity());
        if (host != null) {
            Utils.hideSoftKeyboard(host);
            host.moveToNextStep();
        }
        ui.setLoginStatus(true);
    }
}
