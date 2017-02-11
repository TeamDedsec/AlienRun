package com.example.kaloyanit.alienrun.GameObjects.PowerUps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by Chet on 11.2.2017 г..
 */

public class ExtraLife extends PowerUp {

    public ExtraLife(Bitmap image, int x, int y){
        super(image, x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public void executeEffect(Player player) {
        player.addExtraLife();
    }
}
