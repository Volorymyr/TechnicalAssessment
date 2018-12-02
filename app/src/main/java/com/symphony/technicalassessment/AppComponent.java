package com.symphony.technicalassessment;

import com.symphony.technicalassessment.data.ApiModule;
import com.symphony.technicalassessment.data.DataModule;
import com.symphony.technicalassessment.network.NetworkModule;
import com.symphony.technicalassessment.rx.RxModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
        NetworkModule.class,
        ApiModule.class,
        DataModule.class,
        RxModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder withApplication(App application);
        AppComponent build();
    }
}
