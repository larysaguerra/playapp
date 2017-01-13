package asyral.com.playapp.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by guerra on 10/01/17.
 * ConnectionUtil
 */

public class ConnectionUtil {

    public static OnChangeConnection onChangeConnection;

    public static void setOnChangeConnection(OnChangeConnection onChangeConnection) {
        ConnectionUtil.onChangeConnection = onChangeConnection;
    }

    public static void setOnLine() {
        if (onChangeConnection != null) {
            onChangeConnection.onLine();
        }
    }

    public static void setOffLine() {
        if (onChangeConnection != null) {
            onChangeConnection.offLine();
        }
    }

    public static interface OnChangeConnection {
        void onLine();

        void offLine();
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

}
