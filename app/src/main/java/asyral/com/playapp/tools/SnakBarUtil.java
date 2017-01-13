package asyral.com.playapp.tools;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;

import asyral.com.playapp.R;

/**
 * Created by guerra on 12/01/17.
 * SnakBarUtil
 */

public class SnakBarUtil {

    public static int LENGTH_SHORT = TSnackbar.LENGTH_SHORT;
    public static int LENGTH_LONG = TSnackbar.LENGTH_LONG;
    public static int LENGTH_INDEFINITE = TSnackbar.LENGTH_INDEFINITE;

    public static TSnackbar snackBarTop(Context c, View v, int mjs, int duration) {
        TSnackbar snackbar = TSnackbar.make(v, mjs, duration);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(c, R.color.colorAccentTrans));
        return snackbar;
    }

    public static TSnackbar snackBarTop(Context c, View v, String mjs, int duration) {
        TSnackbar snackbar = TSnackbar.make(v, mjs, duration);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(c, R.color.colorAccentTrans));
        return snackbar;
    }

    public static Snackbar snackBarBotton(Context c, View v, int mjs, int duration) {
        Snackbar snackbar = Snackbar.make(v, mjs, duration);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(c, R.color.colorAccentTrans));
        return snackbar;
    }

    public static Snackbar snackBarBotton(Context c, View v, String mjs, int duration) {
        Snackbar snackbar = Snackbar.make(v, mjs, duration);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(c, R.color.colorAccentTrans));
        return snackbar;
    }
}
