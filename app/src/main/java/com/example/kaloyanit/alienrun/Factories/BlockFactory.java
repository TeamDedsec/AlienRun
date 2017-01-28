package com.example.kaloyanit.alienrun.Factories;

import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.GameObjects.GroundBlock;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

/**
 * Created by Chet on 28.1.2017 г..
 */

public class BlockFactory {
    public static GroundBlock createBlock(int x, int y) {
        return new GroundBlock(BitmapFactory.decodeResource(
                BasicConstants.CURRENT_CONTEXT.getResources(),
                R.drawable.grassmid),
                x,
                y,
                GameConstants.GAME_SPEED);
    }
}
