package net.kosen10s.nicebox.fragment;

import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

    private static final int PACMAN_MAX;
    private static final int PACMAN_LEARGE;
    private static final int PACMAN_MIDDLE;
    private static final int PACMAN_SMALL;

    static {
        PACMAN_MAX    = 100;
        PACMAN_LEARGE = 50;
        PACMAN_MIDDLE = 25;
        PACMAN_SMALL  = 1;
    }

    private static final double PACMAN_MAX_RAITO;
    private static final double PACMAN_LEARGE_RAITO;
    private static final double PACMAN_MIDDLE_RAITO;
    private static final double PACMAN_SMALL_RAITO;
    private static final double PACMAN_MIN_RAITO;

    static {
        PACMAN_MAX_RAITO    = 1.0;
        PACMAN_LEARGE_RAITO = 0.8;
        PACMAN_MIDDLE_RAITO = 0.6;
        PACMAN_SMALL_RAITO  = 0.4;
        PACMAN_MIN_RAITO    = 0.25;
    }

    @Bind(R.id.btn_nice)
    Button mNiceButton;
    @Bind(R.id.text_status)
    TextView mStatusText;
    @Bind(R.id.image_pacman)
    ImageView mPacmanImage;

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
        setPackmanSize(status);
        return view;
    }


    @OnClick(R.id.btn_nice)
    public void onClickNiceButton() {
        if(!advertising) {
            mBluetoothAdvertiseHelper.startAdvertising(mAdvertiseCallback);
            advertising = true;
        }
    }

    public void setPackmanSize(int status) {
        Display disp = getBaseActivity().getWindowManager().getDefaultDisplay();
        Point p = new Point();
        disp.getSize(p);
        ViewGroup.LayoutParams params = mPacmanImage.getLayoutParams();

        double ratio;
        if (status >= PACMAN_MAX) {
            ratio = PACMAN_MAX_RAITO;
        }
        else if (status >= PACMAN_LEARGE) {
            ratio = PACMAN_LEARGE_RAITO;
        }
        else if (status >= PACMAN_MIDDLE){
            ratio = PACMAN_MIDDLE_RAITO;
        }
        else if (status >= PACMAN_SMALL) {
            ratio = PACMAN_SMALL_RAITO;
        }
        else {
            ratio = PACMAN_MIN_RAITO;
        }

        int size = (int)(p.x * ratio);
        params.width = size;
        params.height = size;
        mPacmanImage.setLayoutParams(params);
    }

    AdvertiseCallback mAdvertiseCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "stop advertising");
                    mBluetoothAdvertiseHelper.stopAdvertising(mAdvertiseCallback);
                    advertising = false;
                }
            }, 10000);
        }

        @Override
        public void onStartFailure(int errorCode) {
            super.onStartFailure(errorCode);
            advertising = false;
        }
    };
}