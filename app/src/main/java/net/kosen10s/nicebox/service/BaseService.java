package net.kosen10s.nicebox.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by e10dokup on 2016/04/09
 **/
public abstract class BaseService extends Service {
    private static final String TAG = BaseService.class.getSimpleName();
    private final BaseService self = this;

    protected abstract void execute();

    protected final IBinder mBinder = new Binder() {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public BaseService startResident(Context context) {
        Intent intent = new Intent(context, this.getClass());
        intent.putExtra("type", "start");
        context.startService(intent);

        return this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        execute();
        return super.onStartCommand(intent, flags, startId);
    }

    public void stopResident(Context context) {
        stopSelf();
    }
}