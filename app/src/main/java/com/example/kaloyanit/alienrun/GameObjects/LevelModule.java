package com.example.kaloyanit.alienrun.GameObjects;

import com.example.kaloyanit.alienrun.Enums.BlockSetType;
import com.example.kaloyanit.alienrun.Enums.BlockType;
import com.example.kaloyanit.alienrun.Factories.BlockFactory;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

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
    public boolean isPinchRequired;

    public LevelModule(BlockSetType blockSet, int startX, boolean isPinchRequired) {
        this.blocks = new ArrayList<>();
        this.blockSet = blockSet;
        this.startX = startX;
        this.isPinchRequired = isPinchRequired;
    }

    public void addBlock (BlockType type, int x, int y) {
        blocks.add(BlockFactory.createBlock(blockSet, type, x, y));
        if (x + GameConstants.BLOCK_WIDTH > endX) {
            endX = (x + GameConstants.BLOCK_WIDTH);
        }
        length = endX - startX;
    }

    public void update ()
    {
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).update();
        }
        this.startX += GlobalVariables.GAME_SPEED;
        this.endX += GlobalVariables.GAME_SPEED;
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
