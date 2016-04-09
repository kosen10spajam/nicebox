package net.kosen10s.nicebox.service;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import net.kosen10s.bluetooth.BluetoothScanHelper;
import net.kosen10s.nicebox.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e10dokup on 2016/04/09
 **/
public class BeaconDetectingService extends BaseService {
    private static final String TAG = BeaconDetectingService.class.getSimpleName();
    private final BeaconDetectingService self = this;

    private static final int NOTIFICATION_ID = 1;

    public static BaseService activeService;
    private BluetoothScanHelper mHelper;

    private List<String> mDetectedDeviceList;

    @Override
    public void onCreate() {
        super.onCreate();
        mDetectedDeviceList = new ArrayList<>();
    }

    @Override
    protected void execute() {
        activeService = this;
        mHelper = new BluetoothScanHelper(this);
        mHelper.startScanning(mScanCallback);
        Log.d(TAG, "Start scanning");
    }

    public static void stopResidentIfActive(Context context) {
        if(activeService != null) {
            activeService.stopResident(context);
        }
    }

    private void displayNotification(String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Nicebox");
        builder.setContentText(text);

        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(NOTIFICATION_ID, builder.build());

    }

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            String detectedDevice = result.getDevice().getAddress();
            if(!mDetectedDeviceList.contains(detectedDevice)) {
                displayNotification("イイネを受信したよ！");
                Log.d(TAG, "つらい");
                mDetectedDeviceList.add(detectedDevice);
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };
}