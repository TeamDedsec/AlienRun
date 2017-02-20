package com.example.kaloyanit.alienrun;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KaloyanIT on 2/20/2017.
 */
@Module
public class ApplicationModule {
    private final Context context;

    @Inject
    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return this.context;
    }

    @Provides
    SharedPreferences provideSharedPreferences(){
        return this.context.getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
    }
}
