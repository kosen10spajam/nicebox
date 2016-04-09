package net.kosen10s.nicebox.service;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by e10dokup on 2016/04/09
 **/
public class BeaconDetectingService extends BaseService {
    private static final String TAG = BeaconDetectingService.class.getSimpleName();
    private final BeaconDetectingService self = this;

    private static final int NOTIFICATION_ID = 1;

    public static BaseService activeService;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void execute() {
        activeService = this;

        // TODO: BLE scanning start
    }

    public static void stopResidentIfActive(Context context) {
        if(activeService != null) {
            activeService.stopResident(context);
        }
    }

    private void displayNotification(String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        //builder.setSmallIcon(R.drawable);
        builder.setContentTitle("Current Location");
        builder.setContentText("This app get your current location");
        builder.setSubText(text);

        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(NOTIFICATION_ID, builder.build());

    }
}