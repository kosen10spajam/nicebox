package net.kosen10s.nicebox.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import net.kosen10s.nicebox.R;
import net.kosen10s.nicebox.core.BaseFragment;
import net.kosen10s.nicebox.preference.StatusPreference;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        StatusPreference preference = new StatusPreference(getBaseActivity());
        int status = preference.getStatus();

        ButterKnife.bind(this, view);

        mStatusText.setText(String.valueOf(status));
        return view;
    }
}