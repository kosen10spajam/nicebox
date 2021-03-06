package net.kosen10s.nicebox.service;

import android.app.PendingIntent;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import net.kosen10s.bluetooth.BluetoothScanHelper;
import net.kosen10s.nicebox.MainActivity;
import net.kosen10s.nicebox.R;
import net.kosen10s.nicebox.preference.StatusPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e10dokup on 2016/04/09
 **/
public class BeaconDetectingService extends BaseService {
    private static final String TAG = BeaconDetectingService.class.getSimpleName();
    private final BeaconDetectingService self = this;

    private static final int NOTIFICATION_ID = 1;
    private static final int REQUEST_CODE_NICE = 1;
    public static final String KEY_BUNDLE = "receive_nice";
    private static final int SOUND_POOL_MAX = 6;

    public static BaseService activeService;
    private BluetoothScanHelper mHelper;
    private SoundPool mSoundPool;
    private int mSoundId;

    private List<String> mDetectedDeviceList;

    @Override
    public void onCreate() {
        super.onCreate();
        mDetectedDeviceList = new ArrayList<>();

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .setMaxStreams(SOUND_POOL_MAX)
                .build();

        mSoundId = mSoundPool.load(getApplicationContext(), R.raw.pac_se_eatingcokkie, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSoundPool.release();
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
        mSoundPool.play(mSoundId, 1.0f, 1.0f, 0, 3, 1.0f);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Nicebox");
        builder.setContentText(text);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_BUNDLE, true);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), REQUEST_CODE_NICE, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(NOTIFICATION_ID, builder.build());

    }

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            String detectedDevice = result.getDevice().getAddress();
            if(!mDetectedDeviceList.contains(detectedDevice)) {
                displayNotification("ナイスを受信したよ！");
                StatusPreference preference = new StatusPreference(self);
                preference.increaseStatus();
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