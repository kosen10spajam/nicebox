package xyz.dokup.androidquicktemplate.core;

import dagger.Component;
import xyz.dokup.androidquicktemplate.MainActivity;

@Component(
        modules = MyModule.class
)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
