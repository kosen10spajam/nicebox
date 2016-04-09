package net.kosen10s.nicebox.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.kosen10s.nicebox.R;
import net.kosen10s.nicebox.core.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yasuhiro on 4/9/16.
 */
public class ReceiveFragment extends BaseFragment {
    private static final String TAG = ReceiveFragment.class.getSimpleName();
    private final ReceiveFragment self = this;

    @Bind(R.id.text_nice)
    TextView mTextNice;
    @Bind(R.id.text_time)
    TextView mTextTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receive, container, false);

        ButterKnife.bind(this, view);
        return view;
    }
}
