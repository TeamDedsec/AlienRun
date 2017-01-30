package com.example.kaloyanit.alienrun.Factories;

import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.Enums.BlockType;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.GameObjects.Block;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

/**
 * Created by Chet on 28.1.2017 г..
 */

public class BlockFactory {
    public static Block createBlock(BlockType type, int x, int y) {
        switch (type){
            case GrassMid:
                return new Block(BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.grassmid),
                        x,
                        y,
                        GameConstants.GAME_SPEED,
                        CollisionType.Ground);
            case GrassLeft:
                return new Block(BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.grassleft),
                        x,
                        y,
                        GameConstants.GAME_SPEED,
                        CollisionType.Ground);
            case GrassRight:
                return new Block(BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.grassright),
                        x,
                        y,
                        GameConstants.GAME_SPEED,
                        CollisionType.Ground);
            case GrassHalfMid:
                return new Block(BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.grasshalfmid),
                        x,
                        y,
                        GameConstants.GAME_SPEED,
                        CollisionType.test);
            case GrassHalfRight:
                return new Block(BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.grasshalfright),
                        x,
                        y,
                        GameConstants.GAME_SPEED,
                        CollisionType.test);
            case GrassHalfLeft:
                return new Block(BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.grasshalfleft),
                        x,
                        y,
                        GameConstants.GAME_SPEED,
                        CollisionType.test);
            case Water:
                return new Block(BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.liquidwatertop),
                        x,
                        y,
                        GameConstants.GAME_SPEED,
                        CollisionType.Water);
                default: throw new RuntimeException();
        }
    }
}
