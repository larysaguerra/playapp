package asyral.com.playapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;

import asyral.com.playapp.R;
import asyral.com.playapp.api.Response.Entry;
import asyral.com.playapp.holder.HolderApp;
import asyral.com.playapp.tools.ActivityUtil;
import asyral.com.playapp.tools.Utils;

/**
 * Created by guerra on 12/01/17.
 * DetailAppActivity
 */

public class DetailAppActivity extends ActivityUtil {

    private Toolbar toolbar;
    private CollapsingToolbarLayout ctlLayout;
    private ImageView imgaApp;

    private ImageView imageApp;
    private TextView nameApp;
    private TextView artistAppApp;

    private TextView priceAmountApp;
    private TextView priceCurrencyApp;

    private Button categoryApp;

    private TextView descriptionApp;
    private TextView titleApp;
    private TextView linkApp;

    private TextView rightsApp;
    private TextView dateApp;

    private Entry entry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_app);
        if (getResources().getBoolean(R.bool.is_tablet)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        findViews();
        initVariables();
        setActions();
    }


    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ctlLayout = (CollapsingToolbarLayout) findViewById(R.id.ctlLayout);
        imgaApp = (ImageView) findViewById(R.id.image_detail);

        imageApp = (ImageView) findViewById(R.id.image_app_detail);
        nameApp = (TextView) findViewById(R.id.name_app_detail);
        artistAppApp = (TextView) findViewById(R.id.artist_app_detail);

        priceAmountApp = (TextView) findViewById(R.id.price_amount_app_detail);
        priceCurrencyApp = (TextView) findViewById(R.id.price_currency_app_detail);

        categoryApp = (Button) findViewById(R.id.category_app_detail);

        titleApp = (TextView) findViewById(R.id.title_app_detail);
        descriptionApp = (TextView) findViewById(R.id.decription_app_detail);
        linkApp = (TextView) findViewById(R.id.link_app_detail);

        rightsApp = (TextView) findViewById(R.id.rights_app_detail);
        dateApp = (TextView) findViewById(R.id.date_app_detail);
    }

    private void initVariables() {
        if (getIntent().getExtras().getSerializable("entry") != null) {
            entry = (Entry) getIntent().getExtras().getSerializable("entry");
        }
        startBase(toolbar);
        initToolbar("", CommunityMaterial.Icon.cmd_arrow_left);
        setData();
    }

    private void setData() {
        ctlLayout.setTitle(entry.getImName().getLabel());
        ctlLayout.setCollapsedTitleTextColor(ContextCompat
                .getColor(DetailAppActivity.this, R.color.colorWhite));
        ctlLayout.setExpandedTitleColor(ContextCompat
                .getColor(DetailAppActivity.this, R.color.colorWhite));
        Utils.setImage(DetailAppActivity.this, imgaApp,
                entry.getImImage().get(2).getLabel(),
                480, 480);
        Utils.setImage(DetailAppActivity.this, imageApp,
                entry.getImImage().get(0).getLabel(),
                240, 240);
        nameApp.setText(entry.getImName().getLabel());
        artistAppApp.setText(" " + entry.getImArtist().getLabel());

        priceCurrencyApp.setText(entry.getImPrice().getAttributes().getCurrency());
        priceAmountApp.setText(entry.getImPrice().getAttributes().getAmount());

        categoryApp.setText(entry.getCategory().getAttributes().getLabel());

        descriptionApp.setText(entry.getTitle().getLabel() + " - " + entry.getImArtist().getLabel());
        linkApp.setText(entry.getLink().getAttributes().getHref());
        titleApp.setText(entry.getTitle().getLabel());

        rightsApp.setText(entry.getRights().getLabel());
        dateApp.setText(entry.getImReleaseDate().getAttributes().getLabel());
    }


    private void setActions() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        categoryApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openUrl(DetailAppActivity.this, entry.getCategory().getAttributes().getScheme());
            }
        });
    }

    public static void startDetailAppActivity(Context context,
                                              final HolderApp holderApp,
                                              final Entry entry) {
        Intent intent = new Intent(context, DetailAppActivity.class);
        intent.putExtra("entry", entry);
        Pair<View, String> p1 = new Pair<>((View) holderApp.imageApp, context.getString(R.string.transition_image));
        Pair<View, String> p2 = new Pair<>((View) holderApp.nameApp, context.getString(R.string.transition_name));
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                (ActivityUtil) context, p1, p2);
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
