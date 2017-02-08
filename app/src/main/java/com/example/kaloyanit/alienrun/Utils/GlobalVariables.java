package com.example.kaloyanit.alienrun.Utils;

import com.example.kaloyanit.alienrun.Enums.PlayerType;

/**
 * Created by julian.teofilov on 2/2/2017.
 */

public class GlobalVariables {
    public static int GAME_START_SPEED = 0;
    public static int GAME_SPEED = GameConstants.GAME_SPEED;
    public static int GRAVITY = GameConstants.GRAVITY;
    public static int JUMP_VELOCITY = GameConstants.JUMP_VELOCITY;
    public static int DELAY = GameConstants.PLAYER_ANIMATION_DELAY;
    public static PlayerType ACTIVE_PLAYER = PlayerType.Green;
    public static float xRATIO = BasicConstants.SCREEN_WIDTH / (BasicConstants.BG_WIDTH * 1.0f);
    public static float yRATIO =BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);
}