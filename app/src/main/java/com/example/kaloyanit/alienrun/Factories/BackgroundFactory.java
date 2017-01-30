package com.example.kaloyanit.alienrun.Factories;

import android.graphics.Bitmap;
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
        Bitmap image = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bg_grasslands);
        Bitmap resizedImage = Bitmap.createScaledBitmap(image, BasicConstants.BG_WIDTH, BasicConstants.BG_HEIGHT, false);
        return new Background(resizedImage, GameConstants.GAME_SPEED);
    }

    public static Background createStaticBackground() {
        Bitmap image = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bg_grasslands);
        Bitmap resizedImage = Bitmap.createScaledBitmap(image, BasicConstants.BG_WIDTH, BasicConstants.BG_HEIGHT, false);
        return new Background(resizedImage, 0);
    }
}
