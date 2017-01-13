package asyral.com.playapp.tools;

import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import asyral.com.playapp.R;
import asyral.com.playapp.activity.CategoryActivity;

/**
 * Created by guerra on 10/01/17.
 * ActivityUtil
 */

public class ActivityUtil extends AppCompatActivity {

    private ActionBar actionBar;
    private String titleToolbar;
    private CommunityMaterial.Icon iconToolbar;
    public Toolbar toolbar;

    private String TAG = "ActivityUtil";
    private ConnectionReceiver connectionReceiver = null;
    private Boolean connectionOn = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        //Init icons
        Iconics.init(getApplicationContext());
        Iconics.registerFont(new CommunityMaterial());
        getConnection();
    }

    @Override
    protected void onResume() {
        registerReceiver();
        super.onResume();
    }

    @Override
    protected void onStop() {
        unregisterReceiver();
        super.onStop();
    }

    @Override
    protected void onPause() {
        unregisterReceiver();
        super.onPause();
    }

    public void startBase(Toolbar toolbar) {
        this.toolbar = toolbar;
        titleToolbar = getString(R.string.app_name);
        iconToolbar = CommunityMaterial.Icon.cmd_menu;
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            actionBar = getSupportActionBar();
        }
    }

    public Spannable setColorTextToolbar(String str) {
        Spannable text = new SpannableString(str);
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return text;
    }

    public void initToolbar() {
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(setColorTextToolbar(titleToolbar));
            toolbar.setNavigationIcon(Utils.getIcon(getApplicationContext(), iconToolbar, 20));
        }
    }

    public void initToolbar(String title, CommunityMaterial.Icon icon) {
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(setColorTextToolbar(title));
            toolbar.setNavigationIcon(Utils.getIcon(getApplicationContext(), icon, 20));
        }
    }

    public void initToolbar(String title, String icon) {
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(setColorTextToolbar(title));
            toolbar.setNavigationIcon(Utils.getIcon(getApplicationContext(), icon, 20));
        }
    }

    private void getConnection() {
        ConnectionUtil.setOnChangeConnection(new ConnectionUtil.OnChangeConnection() {
            @Override
            public void onLine() {
                //Utils.logD("TAG", "ON");
                //Utils.toastLong(getApplicationContext(), getApplicationContext().getString(R.string.connection_off));
            }

            @Override
            public void offLine() {
                //Utils.logD("TAG", "OFF");
                if(getApplicationContext() instanceof CategoryActivity){
                    Utils.toastLong(getApplicationContext(),
                            getApplicationContext().getString(R.string.connection_off));
                }
            }
        });
    }

    public void registerReceiver() {
        if (connectionReceiver == null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            connectionReceiver = new ConnectionReceiver();
            registerReceiver(connectionReceiver, filter);
        }
    }

    public void unregisterReceiver() {
        if (connectionReceiver != null) {
            unregisterReceiver(connectionReceiver);
            connectionReceiver = null;
        }
    }
}
