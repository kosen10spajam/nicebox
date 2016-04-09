package net.kosen10s.nicebox.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by e10dokup on 2016/02/24
 **/
public abstract class BaseOnBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        if(intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    onDeviceBoot(context);
                }
            });
        }
    }

    protected abstract void onDeviceBoot(Context context);
}