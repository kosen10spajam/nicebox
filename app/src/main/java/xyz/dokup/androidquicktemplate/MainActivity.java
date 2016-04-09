package xyz.dokup.androidquicktemplate;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import xyz.dokup.androidquicktemplate.core.BaseActivity;
import xyz.dokup.androidquicktemplate.core.BaseFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
