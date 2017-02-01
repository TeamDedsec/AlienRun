package com.example.kaloyanit.alienrun.Factories;

import com.example.kaloyanit.alienrun.Enums.BlockSetType;
import com.example.kaloyanit.alienrun.Enums.BlockType;
import com.example.kaloyanit.alienrun.GameObjects.LevelModule;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

import java.util.Random;

/**
 * Created by julian.teofilov on 31/1/2017.
 */

public class LevelModuleFactory {
    private BlockSetType type;
    private int startPosition = 0;

    public LevelModuleFactory(BlockSetType type) {
        this.type = type;
    }


    public void updateStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public LevelModule getLevelModule() {
        int index = getRandomNumber(0, 3);
        return getLevelModule(index);
    }


    public LevelModule getLevelModule(int index) {
        LevelModule module;

        switch (index) {
            case 0:
                module = createStraightLine();
                startPosition = module.getEndX() + 1;
                return module;
            case 1:
                module = createWaterPool();
                startPosition = module.getEndX() + 1;
                return module;
            case 2:
                module = createFloatingLevel();
                startPosition = module.getEndX() + 1;
                return module;
            case 3:
                module = createBigHole();
                startPosition = module.getEndX() + 1;
                return module;
            case 4:
                module = createStraightLineWithCoins();
                startPosition = module.getEndX() + 1;
                return module;
            default:
                throw new RuntimeException();
        }
    }

    private int getRandomNumber(int min, int max) {
        Random rand = new Random();
        int random = rand.nextInt((max - min) + 1) + min;
        return random;
    }

    private LevelModule createStraightLine() {
        LevelModule module = new LevelModule(type, startPosition);
        for (int i = 0; i < 11; i++) {
            module.addBlock(BlockType.GroundMid, startPosition + (i * GameConstants.BLOCK_WIDTH), BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT);
        }
        return module;
    }

    private LevelModule createStraightLineWithCoins() {
        LevelModule module = new LevelModule(type, startPosition);
        for (int i = 0; i < 11; i++) {
            if (i > 3 && i < 7) {
                module.addBlock(BlockType.Coin, startPosition + (i * GameConstants.BLOCK_WIDTH), BasicConstants.BG_HEIGHT - (GameConstants.BLOCK_HEIGHT * 2));
            }
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
            currentX += GameConstants.BLOCK_WIDTH;
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
            currentX += GameConstants.BLOCK_WIDTH;
        }

        return module;
    }

    private LevelModule createFloatingLevel() {
        LevelModule module = new LevelModule(type, startPosition);
        int currentX = startPosition;
        int positionY = BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT;

        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        module.addBlock(BlockType.GroundRight, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;

        positionY -= 150;
        module.addBlock(BlockType.AirLeft, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        module.addBlock(BlockType.AirMid, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        module.addBlock(BlockType.AirRight, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        positionY += 150;

        module.addBlock(BlockType.GroundLeft, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;

        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        return module;
    }

    private LevelModule createBigHole() {
        LevelModule module = new LevelModule(type, startPosition);
        int currentX = startPosition;
        int positionY = BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT;

        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        module.addBlock(BlockType.GroundRight, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH * 4;

        module.addBlock(BlockType.GroundLeft, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;

        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        return module;
    }
}
