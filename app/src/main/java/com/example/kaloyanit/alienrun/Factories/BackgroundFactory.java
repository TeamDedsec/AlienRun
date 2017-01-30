package com.example.kaloyanit.alienrun.Factories;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.Enums.BackgroundType;
import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

/**
 * Created by Chet on 28.1.2017 г..
 */

public class BackgroundFactory {
    public static Background createBackground(BackgroundType type) {
        Bitmap image;
        switch (type) {
            case Grass:
                image = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bg_grasslands);
                break;
            case Desert:
                image = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bg_desert);
                break;
            case Mushroom:
                image = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bg_shroom);
                break;
            default:
                throw new RuntimeException();
        }
        Bitmap resizedImage = Bitmap.createScaledBitmap(image, BasicConstants.BG_WIDTH, BasicConstants.BG_HEIGHT, false);
        return new Background(resizedImage, GameConstants.GAME_SPEED);
    }
}
