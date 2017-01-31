package com.example.kaloyanit.alienrun.Factories;

import com.example.kaloyanit.alienrun.Enums.BlockSetType;
import com.example.kaloyanit.alienrun.Enums.BlockType;
import com.example.kaloyanit.alienrun.GameObjects.LevelModule;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

import java.util.concurrent.RunnableFuture;
import java.util.logging.Level;

/**
 * Created by julian.teofilov on 31/1/2017.
 */

public class LevelModuleFacotry {
    private BlockSetType type;
    private int startPosition = 0;

    public LevelModuleFacotry(BlockSetType type) {
        this.type = type;
    }

    public LevelModule getLevelModule(int index) {
        LevelModule module;
        switch (index) {
            case 0:
                module = createStraightLine();
                startPosition = module.getEndX();
                return module;
            case 1:
                module = createWaterPool();
                startPosition = module.getEndX();
                return module;
            default:
                throw new RuntimeException();
        }
    }

    private LevelModule createStraightLine() {
        LevelModule module = new LevelModule(type, startPosition);
        for (int i = 0; i < 11; i++) {
            module.addBlock(BlockType.GroundMid, startPosition + (i * GameConstants.BLOCK_WIDTH), BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT);
        }
        return module;
    }

    private LevelModule createWaterPool() {
        LevelModule module = new LevelModule(type, startPosition);
        int currentX = startPosition;
        int positionY = BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT;
        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX = startPosition + (i * GameConstants.BLOCK_WIDTH);
        }

        module.addBlock(BlockType.GroundRight, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        module.addBlock(BlockType.Water, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        module.addBlock(BlockType.Water, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        module.addBlock(BlockType.Water, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        module.addBlock(BlockType.GroundLeft, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;

        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX = startPosition + (i * GameConstants.BLOCK_WIDTH);
        }

        return module;
    }
}
