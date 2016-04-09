package net.kosen10s.nicebox.core;

import dagger.Component;
import net.kosen10s.nicebox.MainActivity;

@Component(
        modules = MyModule.class
)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
