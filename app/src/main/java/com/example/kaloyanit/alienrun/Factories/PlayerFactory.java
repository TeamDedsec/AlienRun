package com.example.kaloyanit.alienrun.Factories;

import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

/**
 * Created by Chet on 28.1.2017 г..
 */

public class PlayerFactory {
    public static Player createPlayer(String color, int x, int y) {
        switch (color) {
            case "green": return new Player(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_walk),
                    BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_jump),
                    BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_duck),
                    x,
                    y,
                    GameConstants.GRAVITY,
                    GameConstants.JUMP_VELOCITY,
                    GameConstants.WALK_FRAMES,
                    GameConstants.JUMP_FRAMES);
            default: throw new RuntimeException();
        }
    }
}
