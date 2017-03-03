package com.example.kaloyanit.alienrun.GameObjects.PowerUps;

import android.graphics.Bitmap;

import com.example.kaloyanit.alienrun.Enums.PowerUpType;
import com.example.kaloyanit.alienrun.GameObjects.Player;

/**
 * Created by Chet on 11.2.2017 г..
 */

public class Mover extends PowerUp {

    public Mover(Bitmap image, int x, int y) {
        super(image, x, y, image.getWidth(), image.getHeight(), PowerUpType.Mover);
    }

    @Override
    public void executeEffect(Player player) {
        player.moveForward();
    }
}
