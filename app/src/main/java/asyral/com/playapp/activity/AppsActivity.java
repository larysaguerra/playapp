package asyral.com.playapp.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.ArrayList;

import asyral.com.playapp.R;
import asyral.com.playapp.adapter.AdapterApp;
import asyral.com.playapp.api.ApiUtil;
import asyral.com.playapp.api.Response.Entry;
import asyral.com.playapp.api.Response.Feed;
import asyral.com.playapp.data.Category;
import asyral.com.playapp.data.EntryUtil;
import asyral.com.playapp.tools.ActivityUtil;
import asyral.com.playapp.tools.ConnectionUtil;
import asyral.com.playapp.tools.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guerra on 11/01/17.
 * AppsActivity
 */

public class AppsActivity extends ActivityUtil implements Callback<Feed> {

    //Views
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeApps;
    private NestedScrollView noConnection;
    private RecyclerView recyclerApps;
    private IconicsImageView iconCategory;
    private TextView nameCategory;

    //Variables
    private String TAG = "AppsActivity";
    private Category category;
    private ApiUtil apiUtil;
    private boolean requestOn = false;
    private AdapterApp adapterApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        if (getResources().getBoolean(R.bool.is_tablet)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        findViews();
        initVariables();
        setActions();
        if (ConnectionUtil.isConnected(this)) {
            noConnection.setVisibility(View.GONE);
            recyclerApps.setVisibility(View.VISIBLE);
            getApps();
        } else {
            getAppsCache();
        }
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        swipeApps = (SwipeRefreshLayout) findViewById(R.id.swipe_apps);
        noConnection = (NestedScrollView) findViewById(R.id.no_connection);
        recyclerApps = (RecyclerView) findViewById(R.id.recycler_apps);
        iconCategory = (IconicsImageView) findViewById(R.id.icon_category);
        nameCategory = (TextView) findViewById(R.id.name_category);
    }

    private void initVariables() {
        if (getIntent().getExtras().getSerializable("category") != null) {
            category = (Category) getIntent().getExtras().getSerializable("category");
        }
        startBase(toolbar);
        initToolbar("", CommunityMaterial.Icon.cmd_arrow_left);
        setData();
        apiUtil = new ApiUtil();
        adapterApp = new AdapterApp();
        if (getResources().getBoolean(R.bool.is_tablet)) {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                    LinearLayoutManager.VERTICAL, false);
            recyclerApps.setLayoutManager(layoutManager);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false);
            recyclerApps.setLayoutManager(layoutManager);
        }
        recyclerApps.setAdapter(adapterApp);
    }

    private void getApps() {
        if (!requestOn) {
            requestOn = true;
            swipeApps.setRefreshing(true);
            apiUtil.getTopFreeApps(category.getType(), 20, this);
        }
    }

    private void getAppsCache() {
        ArrayList<Entry> entryArray = EntryUtil.getEntryByCategory(AppsActivity.this, category.getType());
        if (!entryArray.isEmpty()) {
            adapterApp.addData(entryArray);
        } else {
            Utils.snackTopShort(this, toolbar, getString(R.string.connection_off));
            noConnection.setVisibility(View.VISIBLE);
            recyclerApps.setVisibility(View.GONE);
        }
    }

    private void setData() {
        iconCategory.setIcon(Utils.getIcon(AppsActivity.this, category.getIcon(), 80, Color.YELLOW));
        nameCategory.setText(category.getName());
    }

    private void setActions() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        swipeApps.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getApps();
            }
        });
    }

    @Override
    public void onResponse(Call<Feed> call, Response<Feed> response) {
        requestOn = false;
        swipeApps.setRefreshing(false);
        Utils.logD(TAG, "onResponse");
        if (!response.body().getFeed().getEntry().isEmpty()) {
            EntryUtil.createEntrys(AppsActivity.this, response.body().getFeed().getEntry(), category.getType());
            String id = "";
            ArrayList<Entry> entryArray = new ArrayList<>();
            for (int i = 0; i < response.body().getFeed().getEntry().size(); i++) {
                Entry entry = response.body().getFeed().getEntry().get(i);
                entryArray.add(entry);
            }
            adapterApp.addData(entryArray);
        }
    }

    @Override
    public void onFailure(Call<Feed> call, Throwable t) {
        requestOn = false;
        swipeApps.setRefreshing(false);
        Utils.logD(TAG, "onFailure");
    }

}
