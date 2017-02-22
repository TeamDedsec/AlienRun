package com.example.kaloyanit.alienrun.Views.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KaloyanIT on 2/22/2017.
 */
@Module
public class MainModule {
    @Provides
    MainContracts.View provideMainView() {
        return new MainView();
    }

    @Provides
    MainContracts.Presenter provideMainPresenter(MainContracts.View view) {
        return new MainPresenter(view);
    }
}
