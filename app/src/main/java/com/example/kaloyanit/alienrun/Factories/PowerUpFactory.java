package com.example.kaloyanit.alienrun.Factories;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.Enums.PowerUpType;
import com.example.kaloyanit.alienrun.GameObjects.PowerUps.ExtraLife;
import com.example.kaloyanit.alienrun.GameObjects.PowerUps.Mover;
import com.example.kaloyanit.alienrun.GameObjects.PowerUps.PowerUp;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.Helpers;

/**
 * Created by Chet on 11.2.2017 г..
 */

public class PowerUpFactory {
    public static PowerUp createPowerUp(int x, int y) {
        int rand = Helpers.getRandomNumber(0, 4);
        switch (rand) {
            case 0:
            case 1:
            case 2:
            case 3:
                return createPowerUp(PowerUpType.Mover, x, y);
            case 4:
                return createPowerUp(PowerUpType.ExtraLife, x, y);
            default:
                throw new RuntimeException();
        }
    }

    public static PowerUp createPowerUp(PowerUpType type, int x, int y) {
        Bitmap image;
        switch (type) {
            case ExtraLife:
                image = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.heart);
                return new ExtraLife(image, x, y);
            case Mover:
                image = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.signright);
                return new Mover(image, x, y);
            default:
                throw new RuntimeException();
        }
    }
}
