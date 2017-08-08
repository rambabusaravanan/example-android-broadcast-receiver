package com.rambabusaravanan.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView headsetStateImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Initialize views
        headsetStateImage = (ImageView) findViewById(R.id.headset_state);


        // Initialize receiver
        BroadcastReceiver receiver = new HeadSetListener();

        // Register the receiver
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(receiver, filter);
    }

    private void onSpeaker() {
        headsetStateImage.setImageResource(R.drawable.ic_volume_up_black_24dp);
    }

    private void onHeadSet() {
        headsetStateImage.setImageResource(R.drawable.ic_headset_black_24dp);
    }


    // Reference: https://developer.android.com/reference/android/media/AudioManager.html#ACTION_HEADSET_PLUG
    private class HeadSetListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra("state", -1);
            if (state == 1)
                onHeadSet();
            else
                onSpeaker();
        }
    }

}
