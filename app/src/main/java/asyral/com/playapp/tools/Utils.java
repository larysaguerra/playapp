package asyral.com.playapp.tools;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import asyral.com.playapp.R;

/**
 * Created by guerra on 10/01/17.
 * Utils
 */

public class Utils {

    private static String TAG = "Utils";
    private static String TAG_APP = "AppTag";

    public static IconicsDrawable getIcon(Context context, String icon, int color, int size) {
        return new IconicsDrawable(context, icon).color(color).sizeDp(size);
    }

    public static IconicsDrawable getIcon(Context context, String icon, int size) {
        return new IconicsDrawable(context, icon).sizeDp(size).color(Color.WHITE);
    }

    public static IconicsDrawable getIcon(Context context, CommunityMaterial.Icon icon, int size) {
        return new IconicsDrawable(context, icon).sizeDp(size).color(Color.WHITE);
    }

    /**
     * ImageRealmObject
     */
    public static void setImage(final Context context, final ImageView img, final String url, final int width, final int height) {
        if (url != null) {
            Callback diskfailCallback = new Callback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {
                    Picasso.with(context)
                            .load(url)
                            .error(getDefaultImage(context, Math.max(width, height)))
                            .into(img, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {
                                    logD(TAG, "Picasso - Could not fetch image");
                                }
                            });
                }
            };
            if (url != null && url.compareTo("") != 0) {
                Picasso.with(context)
                        .load(url)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .error(getDefaultImage(context, Math.max(width, height)))
                        .resize(width, height)
                        .centerCrop()
                        .into(img, diskfailCallback);
            }
        }
    }

    private static Drawable getDefaultImage(Context context, int mesuare) {
        return getIcon(context, CommunityMaterial.Icon.cmd_image_broken_variant, mesuare);
    }

    /**
     * Log
     */
    public static void logD(String className, String log) {
        Log.d(TAG_APP, className + " -> " + log);
    }

    public static void logI(String className, String log) {
        Log.i(TAG_APP, className + " -> " + log);
    }

    public static void logE(String className, String log) {
        Log.e(TAG_APP, className + " -> " + log);
    }

    /**
     * Toast
     */
    public static void toastLong(Context context, int s) {
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        toast.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkTrans));
        toast.show();
    }

    public static void toastLong(Context context, String s) {
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        toast.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkTrans));
        toast.show();
    }

    public static void toastShort(Context context, int s) {
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        toast.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkTrans));
        toast.show();

    }

    public static void snackshort(Context context, String s) {
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        toast.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkTrans));
        toast.show();
    }

    /**
     * SnakBar
     */
    public static void snackBottonShort(Context context, View v, int s) {
        SnakBarUtil.snackBarBotton(context, v, s, SnakBarUtil.LENGTH_SHORT).show();
    }

    public static void snackBottonShort(Context context, View v, String s) {
        SnakBarUtil.snackBarBotton(context, v, s, SnakBarUtil.LENGTH_SHORT).show();
    }

    public static void snackBottonLong(Context context, View v, int s) {
        SnakBarUtil.snackBarBotton(context, v, s, SnakBarUtil.LENGTH_LONG).show();
    }

    public static void snackBottonLong(Context context, View v, String s) {
        SnakBarUtil.snackBarBotton(context, v, s, SnakBarUtil.LENGTH_LONG).show();
    }

    public static void snackTopShort(Context context, View v, int s) {
        SnakBarUtil.snackBarTop(context, v, s, SnakBarUtil.LENGTH_SHORT).show();
    }

    public static void snackTopShort(Context context, View v, String s) {
        SnakBarUtil.snackBarTop(context, v, s, SnakBarUtil.LENGTH_SHORT).show();
    }

    public static void snackTopLong(Context context, View v, int s) {
        SnakBarUtil.snackBarTop(context, v, s, SnakBarUtil.LENGTH_LONG).show();
    }

    public static void snackTopLong(Context context, View v, String s) {
        SnakBarUtil.snackBarTop(context, v, s, SnakBarUtil.LENGTH_LONG).show();
    }

    public static boolean openUrl(final Context context, final String url) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
