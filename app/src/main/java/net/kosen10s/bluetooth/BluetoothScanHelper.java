package net.kosen10s.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;

import net.kosen10s.nicebox.R;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by e10dokup on 2016/04/01
 **/
public class BluetoothScanHelper {
    private static final String TAG = BluetoothScanHelper.class.getSimpleName();
    private final BluetoothScanHelper self = this;

    private BluetoothLeScanner mBluetoothLeScanner;
    private boolean mScanning;
    private Context mContext;

    public BluetoothScanHelper(Context context) {
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        mBluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        mContext = context;
    }

    public boolean isScanning() {
        return mScanning;
    }

    public void startScanning(ScanCallback callback) {
        final int appleManufactureId = 0x004C;
        final byte[] manufacturerData = new byte[23];
        ByteBuffer byteBuffer = ByteBuffer.wrap(manufacturerData);
        byteBuffer.put((byte) 0x02);
        byteBuffer.put((byte) 0x15);

        final UUID uuid = UUID.fromString(mContext.getString(R.string.beacon_uuid));
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());

        byteBuffer.putShort((short) 0x0A);
        byteBuffer.putShort((short) 0x1F);

        List<ScanFilter> scanFilters = new ArrayList<>();
        ScanFilter.Builder filterBuilder = new ScanFilter.Builder();
        filterBuilder.setManufacturerData(appleManufactureId, manufacturerData);
        scanFilters.add(filterBuilder.build());

        ScanSettings.Builder settingsBuilder = new ScanSettings.Builder();
        settingsBuilder.setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY);
        ScanSettings scanSettings = settingsBuilder.build();

        mBluetoothLeScanner.startScan(scanFilters, scanSettings, callback);

        mScanning = true;
    }

    public void stopScanning(ScanCallback callback) {
        mBluetoothLeScanner.stopScan(callback);
        mScanning = false;
    }
}