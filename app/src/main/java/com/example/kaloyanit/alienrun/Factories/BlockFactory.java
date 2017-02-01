package com.example.kaloyanit.alienrun.Factories;

import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.Enums.BlockSetType;
import com.example.kaloyanit.alienrun.Enums.BlockType;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.GameObjects.Block;
import com.example.kaloyanit.alienrun.GameObjects.BlockSet;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

/**
 * Created by Chet on 28.1.2017 г..
 */

public class BlockFactory {
    public static Block createBlock(BlockSetType setType, BlockType blockType, int x, int y) {
        BlockSet blockSet = BlockSetFactory.createBlockSet(setType);

        switch (blockType) {
            case GroundMid:
                return new Block(blockSet.getMiddleGround(), "GroundMid",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        GameConstants.GAME_SPEED,
                        CollisionType.Ground);
            case GroundLeft:
                return new Block(blockSet.getLeftGround(), "GroundLeft",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        GameConstants.GAME_SPEED,
                        CollisionType.Ground);
            case GroundRight:
                return new Block(blockSet.getRightGround(), "GroundRight,",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        GameConstants.GAME_SPEED,
                        CollisionType.Ground);
            case AirMid:
                return new Block(blockSet.getMiddleAir(), "AirMid",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT / 2,
                        GameConstants.GAME_SPEED,
                        CollisionType.Ground);
            case AirRight:
                return new Block(blockSet.getRightAir(), "AirRight",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT / 2,
                        GameConstants.GAME_SPEED,
                        CollisionType.Ground);
            case AirLeft:
                return new Block(blockSet.getLeftAir(), "AirLeft",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT / 2,
                        GameConstants.GAME_SPEED,
                        CollisionType.Ground);
            default:
                return createBlock(blockType, x, y);
        }
    }

    public static Block createBlock(BlockType blockType, int x, int y) {
        switch (blockType) {
            case Water:
                return new Block(BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.liquidwatertop),
                        "Water",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        GameConstants.GAME_SPEED,
                        CollisionType.Water);
            case Coin:
                return new Block(BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.coingold),
                        "Coin",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        GameConstants.GAME_SPEED,
                        CollisionType.Coin);
            default:
                throw new RuntimeException();
        }
    }
}
