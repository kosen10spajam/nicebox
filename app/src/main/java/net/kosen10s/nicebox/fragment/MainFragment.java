package net.kosen10s.nicebox.fragment;

import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.kosen10s.bluetooth.BluetoothAdvertiseHelper;
import net.kosen10s.nicebox.R;
import net.kosen10s.nicebox.core.BaseFragment;
import net.kosen10s.nicebox.preference.StatusPreference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by e10dokup on 2016/04/09
 **/
public class MainFragment extends BaseFragment {
    private static final String TAG = MainFragment.class.getSimpleName();
    private final MainFragment self = this;

    @Bind(R.id.btn_nice)
    Button mNiceButton;
    @Bind(R.id.text_status)
    TextView mStatusText;

    private BluetoothAdvertiseHelper mBluetoothAdvertiseHelper;
    private boolean advertising = false;
    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        StatusPreference preference = new StatusPreference(getBaseActivity());
        int status = preference.getStatus();

        ButterKnife.bind(this, view);

        mStatusText.setText(String.valueOf(status));
        mBluetoothAdvertiseHelper = new BluetoothAdvertiseHelper(getBaseActivity());
        mHandler = new Handler();
        return view;
    }

    @OnClick(R.id.btn_nice)
    public void onClickNiceButton() {
        if(!advertising) {
            mBluetoothAdvertiseHelper.startAdvertising(mAdvertiseCallback);
            advertising = true;
        }
    }

    AdvertiseCallback mAdvertiseCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBluetoothAdvertiseHelper.stopAdvertising(mAdvertiseCallback);
                    advertising = false;
                }
            }, 10000);
        }

        @Override
        public void onStartFailure(int errorCode) {
            super.onStartFailure(errorCode);
        }
    };
}