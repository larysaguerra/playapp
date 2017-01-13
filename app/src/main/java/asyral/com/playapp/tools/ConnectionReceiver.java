package asyral.com.playapp.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by guerra on 10/01/17.
 * ConnectionReceiver
 */

public class ConnectionReceiver  extends BroadcastReceiver {

    private String TAG = "ConnectionReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectionUtil.isConnected(context)) {
            //Utils.logI(TAG, "Connection OnLine");
            ConnectionUtil.setOnLine();
        } else {
            ConnectionUtil.setOffLine();
            //Utils.logI(TAG,  "Connection OffLine");
        }
    }

}
