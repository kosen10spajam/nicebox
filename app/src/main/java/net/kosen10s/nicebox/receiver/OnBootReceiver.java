package net.kosen10s.nicebox.receiver;

import android.content.Context;

import net.kosen10s.nicebox.service.BeaconDetectingService;

/**
 * Created by e10dokup on 2016/02/24
 **/
public class OnBootReceiver extends BaseOnBootReceiver {
    private static final String TAG = OnBootReceiver.class.getSimpleName();
    private final OnBootReceiver self = this;

    @Override
    protected void onDeviceBoot(Context context) {
        new BeaconDetectingService().startResident(context);
    }
}