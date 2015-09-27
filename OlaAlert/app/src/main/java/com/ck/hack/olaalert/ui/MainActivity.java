package com.ck.hack.olaalert.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ck.hack.olaalert.R;
import com.ck.hack.olaalert.app.DataManager;
import com.ck.hack.olaalert.app.OlaAlertApp;
import com.ck.hack.olaalert.app.UserInfo;
import com.ck.hack.olaalert.utils.Utils;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = MainActivity.class.getSimpleName();

    private static final int SETUP_SCREEN_REQUEST = 2;
    public static final String NAV_ITEM_ID = "nav_item";
    private FragmentManager mFragMan;
    private DataManager mDataMan;

    public enum Screen {
        HOME_PAGE,
        DRIVER_INFO,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OlaAlertApp app = (OlaAlertApp) getApplication();
        mDataMan = app.getDataManager();
        mFragMan = getSupportFragmentManager();

        UserInfo userInfo = mDataMan.getUserInfo();
        if (!userInfo.isLoggedIn()) {
            Log.v(LOGTAG, "New user");
            handleNewUser();
        } else {
            showPage(Screen.HOME_PAGE);
        }
    }

    public void showPage(Screen screen) {
        Fragment f ;
        if (screen == Screen.HOME_PAGE) {
            f = HomeFragment.getInstance();
        } else {
            f = null;
        }

        mFragMan.beginTransaction().replace(R.id.fragment, f).commitAllowingStateLoss();
    }

    private void handleNewUser() {
        Intent loginIntent = new Intent(MainActivity.this, SetupActivity.class);
        startActivityForResult(loginIntent, SETUP_SCREEN_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        Log.v(LOGTAG, "onActivityResult+ - " + requestCode);
        switch (requestCode) {
            case SETUP_SCREEN_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    Log.v(LOGTAG, "Setup completed!");
                    showPage(Screen.HOME_PAGE);
                } else {
                    Log.e(LOGTAG, "Setup did not complete. Exit");
                    finish();
                }
                break;
        }
    }

            @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
