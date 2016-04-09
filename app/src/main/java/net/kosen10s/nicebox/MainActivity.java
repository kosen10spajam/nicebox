package net.kosen10s.nicebox;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import net.kosen10s.nicebox.core.BaseActivity;
import net.kosen10s.nicebox.core.BaseFragment;
import net.kosen10s.nicebox.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //まだFragmentは置かれていない
        setContentView(R.layout.activity_main);

        //Fragmentを置く
        replaceFragment(new MainFragment(), false);

    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean recordBackstack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        if(recordBackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void popFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }
}
