package com.rambabusaravanan.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;

/**
 * Created by andro on 8/8/17.
 */

public class ConnectionListener extends BroadcastReceiver {

    public int getConnectivityStatus(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = manager.getActiveNetworkInfo();
        if (null != info) {
            if (info.getType() == TYPE_WIFI)
                return TYPE_WIFI;

            if (info.getType() == TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return -1;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = getConnectivityStatus(context);

        String text = "No Network Connection ..";
        if (status == TYPE_WIFI) {
            text = "Wifi Connected ..";
        } else if (status == TYPE_MOBILE) {
            text = "Mobile Data Connected ..";
        }
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}