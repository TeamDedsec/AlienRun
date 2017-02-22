package com.example.kaloyanit.alienrun.Views;

import com.example.kaloyanit.alienrun.Views.achievements.AchievementsModule;
import com.example.kaloyanit.alienrun.Views.main.MainModule;
import com.example.kaloyanit.alienrun.Views.players.PlayersModule;
import com.example.kaloyanit.alienrun.Views.settings.SettingsModule;

import dagger.Module;

/**
 * Created by KaloyanIT on 2/20/2017.
 */
@Module(includes = {SettingsModule.class, PlayersModule.class, AchievementsModule.class, MainModule.class})
public class ViewsModule {
}
