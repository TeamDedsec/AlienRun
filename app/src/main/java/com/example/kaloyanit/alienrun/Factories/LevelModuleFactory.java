package com.example.kaloyanit.alienrun.Factories;

import com.example.kaloyanit.alienrun.Enums.BlockSetType;
import com.example.kaloyanit.alienrun.Enums.BlockType;
import com.example.kaloyanit.alienrun.GameObjects.Block;
import com.example.kaloyanit.alienrun.GameObjects.LevelModule;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Utils.Helpers;

/**
 * Created by julian.teofilov on 31/1/2017.
 */

public class LevelModuleFactory {
    private BlockSetType type;
    private int startPosition = 0;

    public LevelModuleFactory(BlockSetType type) {
        this.type = type;
    }

    public int resetEndPosition(LevelModule module) {
        int length = module.getBlocks().size();
        Block last = module.getBlocks().get(length - 1);
        return last.getX() + last.getWidth();
    }

    public void update() {
        this.startPosition += GlobalVariables.GAME_SPEED;
    }

    public void changeBlockType() {
        int rand = Helpers.getRandomNumber(0, 3);
        switch (rand) {
            case 0:
                this.type = BlockSetType.Grass;
                break;
            case 1:
                this.type = BlockSetType.Sand;
                break;
            case 2:
                this.type = BlockSetType.Rock;
                break;
            case 3:
                this.type = BlockSetType.Snow;
                break;
            default:
                throw new RuntimeException();
        }
    }

    public LevelModule getLevelModule() {
        int index = Helpers.getRandomNumber(0, 6);
        return getLevelModule(index);
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
            case 2:
                module = createFloatingLevel();
                startPosition = module.getEndX();
                return module;
            case 3:
                module = createBigHole();
                startPosition = module.getEndX();
                return module;
            case 4:
                module = createStraightLineWithCoins();
                startPosition = module.getEndX();
                return module;
            case 5:
                module = createFloatingLevelWithSpinner();
                startPosition = module.getEndX();
                return module;
            case 6:
                module = createSmallWallHole();
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

        module.setEndX(resetEndPosition(module));
        return module;
    }

    private LevelModule createStraightLineWithCoins() {
        LevelModule module = new LevelModule(type, startPosition);
        for (int i = 0; i < 11; i++) {
            if (i > 3 && i < 7) {
                module.addBlock(BlockType.Coin, startPosition + (i * GameConstants.BLOCK_WIDTH), BasicConstants.BG_HEIGHT - (GameConstants.BLOCK_HEIGHT * 2));
            }
            module.addBlock(BlockType.GroundMid, startPosition + (i * GameConstants.BLOCK_WIDTH), BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT);
            module.setEndX((i * GameConstants.BLOCK_WIDTH) + GameConstants.BLOCK_WIDTH);
        }

        module.setEndX(resetEndPosition(module));
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

        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.Coin, currentX, positionY - (GameConstants.BLOCK_HEIGHT * 2));
            module.addBlock(BlockType.Water, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        module.addBlock(BlockType.GroundLeft, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;

        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        module.setEndX(resetEndPosition(module));
        return module;
    }

    private LevelModule createFloatingLevel() {
        LevelModule module = new LevelModule(type, startPosition);
        int currentX = startPosition;
        int positionY = BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT;

        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            if (i == 2) {
                module.addBlock(BlockType.SpinnerHalf, currentX, positionY - (GameConstants.BLOCK_HEIGHT / 2) + 5);
            }
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

        int coinAdjust = 170;
        module.addBlock(BlockType.GroundLeft, currentX, positionY);
        module.addBlock(BlockType.Coin, currentX, positionY - (GameConstants.BLOCK_HEIGHT) - coinAdjust);
        currentX += GameConstants.BLOCK_WIDTH;

        for (int i = 0; i < 3; i++) {
            if (i != 2) {
                coinAdjust += GameConstants.BLOCK_HEIGHT / 2;
                module.addBlock(BlockType.Coin, currentX, positionY - (GameConstants.BLOCK_HEIGHT) - coinAdjust);
            }
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        module.setEndX(resetEndPosition(module));
        return module;
    }

    private LevelModule createFloatingLevelWithSpinner() {
        LevelModule module = new LevelModule(type, startPosition);
        int currentX = startPosition;
        int positionY = BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT;

        module.addBlock(BlockType.GroundMid, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        module.addBlock(BlockType.GroundRight, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        positionY -= 150;
        module.addBlock(BlockType.AirLeft, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;

        for (int i = 0; i < 6; i++) {
            module.addBlock(BlockType.AirMid, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
            if (i == 2) {
                module.addBlock(BlockType.SpinnerHalf, currentX, positionY - (GameConstants.BLOCK_HEIGHT / 2) + 5);
            }
        }

        module.addBlock(BlockType.AirRight, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        positionY += 150;

        int coinAdjust = 170;
        module.addBlock(BlockType.GroundLeft, currentX, positionY);
        module.addBlock(BlockType.Coin, currentX, positionY - (GameConstants.BLOCK_HEIGHT) - coinAdjust);
        currentX += GameConstants.BLOCK_WIDTH;
        for (int i = 0; i < 2; i++) {
            coinAdjust += GameConstants.BLOCK_HEIGHT / 2;
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            module.addBlock(BlockType.Coin, currentX, positionY - (GameConstants.BLOCK_HEIGHT) - coinAdjust);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        module.setEndX(resetEndPosition(module));
        return module;
    }

    private LevelModule createBigHole() {
        LevelModule module = new LevelModule(type, startPosition);
        int currentX = startPosition;
        int positionY = BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT;

        for (int i = 0; i < 2; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        module.addBlock(BlockType.GroundRight, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH * 2;

        for (int i = 0; i < 3; i++) {
            module.addBlock(BlockType.Coin, currentX, positionY - GameConstants.BLOCK_HEIGHT - 200);
            currentX += GameConstants.BLOCK_WIDTH;
        }
        currentX += GameConstants.BLOCK_WIDTH;

        module.addBlock(BlockType.GroundLeft, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;

        for (int i = 0; i < 2; i++) {
            module.addBlock(BlockType.GroundMid, currentX, positionY);
            currentX += GameConstants.BLOCK_WIDTH;
        }

        module.setEndX(resetEndPosition(module));
        return module;
    }

    public LevelModule createSmallWallHole() {
        LevelModule module = new LevelModule(type, startPosition);
        int currentX = startPosition;
        int positionY = BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT;

        module.addBlock(BlockType.GroundRight, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH * 2;

/*        for (int i = 0; i < 5; i++) {
            if (i == 4) {
                module.addBlock(BlockType.GroundLeft, currentX, positionY - (i * GameConstants.BLOCK_HEIGHT));
            } else if (i == 5) {
                continue;
            } else if (i == 6) {
                module.addBlock(BlockType.GroundBottomLeft, currentX, positionY - (i * GameConstants.BLOCK_HEIGHT));
            } else {
                module.addBlock(BlockType.GroundFullWall, currentX, positionY - (i * GameConstants.BLOCK_HEIGHT));
            }
        }
        currentX += GameConstants.BLOCK_WIDTH;*/

        int height = (BasicConstants.BG_HEIGHT / GameConstants.BLOCK_HEIGHT) + 1;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < height; j++) {
                if (j == 4) {
                    continue;
                }

                if (i == 0) {
                    if (j == 3) {
                        module.addBlock(BlockType.GroundLeft, currentX, positionY - (j * GameConstants.BLOCK_HEIGHT));
                        continue;
                    }
                } else if (i == 5) {
                    if (j == 3) {
                        module.addBlock(BlockType.GroundRight, currentX, positionY - (j * GameConstants.BLOCK_HEIGHT));
                        continue;
                    }
                }

                if (j == 3) {
                    module.addBlock(BlockType.GroundMid, currentX, positionY - (j * GameConstants.BLOCK_HEIGHT));
                    continue;
                } else {
                    if (i == 0 && j < 4) {
                        module.addBlock(BlockType.GroundFullWall, currentX, positionY - (j * GameConstants.BLOCK_HEIGHT));
                        continue;
                    } else if (i == 1 && j == 5) {
                        module.addBlock(BlockType.GroundBottomLeft, currentX, positionY - (j * GameConstants.BLOCK_HEIGHT));
                        continue;
                    } else if (i == 1 && j > 5) {
                        module.addBlock(BlockType.GroundFullWall, currentX, positionY - (j * GameConstants.BLOCK_HEIGHT));
                        continue;
                    } else {
                        if (i == 0 && j > 3) {
                            continue;
                        } else if (i == 5 && j == 5) {
                            module.addBlock(BlockType.GroundBottomRight, currentX, positionY - (j * GameConstants.BLOCK_HEIGHT));
                            continue;
                        } else {
                            module.addBlock(BlockType.GroundBottomMiddle, currentX, positionY - (j * GameConstants.BLOCK_HEIGHT));
                            continue;
                        }
                    }
                }

            }
            currentX += GameConstants.BLOCK_WIDTH;
        }

        currentX += GameConstants.BLOCK_WIDTH;
        module.addBlock(BlockType.GroundLeft, currentX, positionY);
        currentX += GameConstants.BLOCK_WIDTH;
        module.setEndX(currentX);

        return module;
    }
}
