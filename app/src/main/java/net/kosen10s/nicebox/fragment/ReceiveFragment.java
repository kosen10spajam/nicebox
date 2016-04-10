package net.kosen10s.nicebox.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.kosen10s.nicebox.R;
import net.kosen10s.nicebox.core.BaseFragment;
import net.kosen10s.nicebox.preference.StatusPreference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yasuhiro on 4/9/16.
 */
public class ReceiveFragment extends BaseFragment {
    private static final String TAG = ReceiveFragment.class.getSimpleName();
    private final ReceiveFragment self = this;

    @Bind(R.id.text_nice_point_value)
    TextView mTextNicePoint;
    @Bind(R.id.btn_back)
    Button mBackButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receive, container, false);

        ButterKnife.bind(this, view);

        StatusPreference preference = new StatusPreference(getBaseActivity());
        int status = preference.getStatus();
        mTextNicePoint.setText(String.valueOf(status));
        return view;
    }

    @OnClick(R.id.btn_back)
    public void onClickBackButton() {
        popFragment();
    }
}
