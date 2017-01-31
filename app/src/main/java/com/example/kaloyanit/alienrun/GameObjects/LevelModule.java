package com.example.kaloyanit.alienrun.GameObjects;

import com.example.kaloyanit.alienrun.Enums.BlockSetType;
import com.example.kaloyanit.alienrun.Enums.BlockType;
import com.example.kaloyanit.alienrun.Factories.BlockFactory;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

import java.util.ArrayList;

/**
 * Created by julian.teofilov on 31/1/2017.
 */

public class LevelModule {
    ArrayList<Block> blocks;
    private BlockSetType blockSet;
    private int startX;
    private int endX;
    private int length;

    public LevelModule(BlockSetType blockSet, int startX) {
        this.blocks = new ArrayList<>();
        this.blockSet = blockSet;
        this.startX = startX;
    }

    public void addBlock (BlockType type, int x, int y) {
        blocks.add(BlockFactory.createBlock(blockSet, type, x, y));
        endX = (x + GameConstants.BLOCK_WIDTH);
        length = endX - startX;
    }

    public void update ()
    {
        this.startX += GameConstants.GAME_SPEED;
        this.endX += GameConstants.GAME_SPEED;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) { this.startX = startX; }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) { this.endX = endX; }

    public int getLength() {
        return length;
    }

    public void setLength(int length) { this.length = length; }
}
