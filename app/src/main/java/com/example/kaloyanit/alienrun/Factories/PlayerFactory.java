package com.example.kaloyanit.alienrun.Factories;

import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.Enums.PlayerType;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

/**
 * Created by Chet on 28.1.2017 г..
 */

public class PlayerFactory {
    public static Player createPlayer(PlayerType type, int x, int y) {
        switch (type) {
            case Green:
                return new Player(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_walk),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_jump),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_duck),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_hurt),
                        x, y,
                        GameConstants.GAME_SPEED,
                        GameConstants.GRAVITY,
                        GameConstants.JUMP_VELOCITY,
                        GameConstants.WALK_FRAMES,
                        GameConstants.JUMP_FRAMES,
                        GameConstants.HIGH_POINT_FRAMES,
                        GameConstants.DUCK_FRAMES,
                        1, 1);
            case Blue:
                return new Player(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p2_walk),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p2_jump),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p2_duck),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p2_hurt),
                        x, y,
                        GameConstants.GAME_SPEED,
                        GameConstants.GRAVITY,
                        GameConstants.JUMP_VELOCITY,
                        GameConstants.WALK_FRAMES,
                        GameConstants.JUMP_FRAMES,
                        GameConstants.HIGH_POINT_FRAMES,
                        GameConstants.DUCK_FRAMES,
                        1, 2);
            case Pink:
                return new Player(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p3_walk),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p3_jump),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p3_duck),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p3_hurt),
                        x, y,
                        GameConstants.GAME_SPEED,
                        GameConstants.GRAVITY,
                        GameConstants.JUMP_VELOCITY,
                        GameConstants.WALK_FRAMES,
                        GameConstants.JUMP_FRAMES,
                        GameConstants.HIGH_POINT_FRAMES,
                        GameConstants.DUCK_FRAMES,
                        2, 1);
            default:
                throw new RuntimeException();
        }
    }
}
