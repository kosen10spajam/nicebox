package net.kosen10s.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;

import net.kosen10s.nicebox.R;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created by e10dokup on 2016/04/09
 **/
public class BluetoothAdvertiseHelper {
    private static final String TAG = BluetoothAdvertiseHelper.class.getSimpleName();
    private final BluetoothAdvertiseHelper self = this;

    private BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    private boolean mScanning;
    private Context mContext;

    public BluetoothAdvertiseHelper(Context context) {
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        mBluetoothLeAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
        mContext = context;
    }

    public void startAdvertising() {
        // Advertiseのセッティング
        AdvertiseSettings.Builder settingsBuilder = new AdvertiseSettings.Builder();
        settingsBuilder.setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED);
        settingsBuilder.setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH);
        settingsBuilder.setTimeout(0);
        settingsBuilder.setConnectable(false);

        // Advertise data
        final byte[] manufactureData = new byte[23];
        ByteBuffer byteBuffer = ByteBuffer.wrap(manufactureData);
        byteBuffer.put((byte) 0x02);
        byteBuffer.put((byte) 0x15);

        final UUID uuid = UUID.fromString(mContext.getString(R.string.beacon_uuid));
    }

    public void stopAdvertising() {

    }
}