package com.example.kaloyanit.alienrun.Factories;

import android.graphics.Bitmap;
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
                return new Block(blockSet.getMiddleGround(), 1, "GroundMid",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        CollisionType.Ground);
            case GroundLeft:
                return new Block(blockSet.getLeftGround(), 1, "GroundLeft",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        CollisionType.Wall);
            case GroundRight:
                return new Block(blockSet.getRightGround(), 1, "GroundRight,",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        CollisionType.Ground);
            case AirMid:
                return new Block(blockSet.getMiddleAir(), 1, "AirMid",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT / 2,
                        CollisionType.Ground);
            case AirRight:
                return new Block(blockSet.getRightAir(), 1, "AirRight",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT / 2,
                        CollisionType.Ground);
            case AirLeft:
                return new Block(blockSet.getLeftAir(), 1, "AirLeft",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT / 2,
                        CollisionType.Wall);
            default:
                return createBlock(blockType, x, y);
        }
    }

    public static Block createBlock(BlockType blockType, int x, int y) {
        Bitmap[] anim;
        switch (blockType) {
            case Water:
                anim = new Bitmap[2];
                anim[0] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.liquidwatertop);
                anim[1] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.liquidwatertop2);
                return new Block(anim, 2, "Water",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        CollisionType.Water);
            case Coin:
                anim = new Bitmap[1];
                anim[0] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.coingold);
                return new Block(anim, 1, "Coin",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        CollisionType.Coin);
            case SpinnerHalf:
                anim = new Bitmap[2];
                anim[0] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.spinnerhalf);
                anim[1] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.spinnerhalf_spin);
                return new Block(anim, 2, "SpinnerHalf",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT / 2,
                        CollisionType.Enemy);
            case Spinner:
                anim = new Bitmap[2];
                anim[0] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.spinner);
                anim[1] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.spinner_spin);
                return new Block(anim, 2, "Spinner",
                        x, y, GameConstants.BLOCK_WIDTH, GameConstants.BLOCK_HEIGHT,
                        CollisionType.Enemy);
            default:
                throw new RuntimeException();
        }
    }
}
