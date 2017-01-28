package com.example.kaloyanit.alienrun.Factories;

import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

/**
 * Created by Chet on 28.1.2017 г..
 */

public class BackgroundFactory {
    public static Background createBackground() {
        return new Background(BitmapFactory.decodeResource(
                BasicConstants.CURRENT_CONTEXT.getResources(),
                R.drawable.bg_grasslands),
                GameConstants.GAME_SPEED);
    }
}
