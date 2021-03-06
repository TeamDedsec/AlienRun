package com.example.kaloyanit.alienrun.Utils;

import android.graphics.Rect;

import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.GameObjects.Block;
import com.example.kaloyanit.alienrun.GameObjects.GameObject;
import com.example.kaloyanit.alienrun.GameObjects.LevelModule;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.SoundPlayers.SoundPlayer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by julian.teofilov on 2/2/2017.
 */

public class Helpers {
    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        int random = rand.nextInt((max - min) + 1) + min;
        return random;
    }

    public static boolean checkCollision(GameObject a, GameObject b) {
        return Rect.intersects(a.getRectangle(), b.getRectangle());
    }

    public static boolean checkPreciseCollision(GameObject a, GameObject b) {
        return Rect.intersects(a.getRectangle(), new Rect(b.getX() + 20, b.getY() + 20, b.getX() + b.getWidth() - 20, b.getY() + b.getHeight() - 20));
    }

    public static CollisionType checkCollision(Player player, ArrayList<LevelModule> modules) {
        //TODO: JT: Update and refactor collision
        player.setNextToWall(false);
        int moveCorrection = 0;
        if (player.isMovingForward()) {
            moveCorrection++;
        }

        //Store all found collisions in a sorted list by their priority
        Map<Integer, CollisionType> types = new TreeMap<>();
        for (int j = 0; j < modules.size(); j++) {
            LevelModule module = modules.get(j);
            //skip modules that the player is not in
            if (Rect.intersects(player.getRectangle(), new Rect(module.getStartX(), 0, module.getEndX(), BasicConstants.BG_HEIGHT))) {
                for (int i = 0; i < module.getBlocks().size(); i++) {
                    Block currBlock = module.getBlocks().get(i);
                    if (Rect.intersects(player.getRectangle(), currBlock.getRectangle())) {
                        if (currBlock.getCollisionType() == CollisionType.Coin) {
                            //Lower the collision radius if it's a coin
                            if (checkPreciseCollision(player, currBlock)) {
                                GlobalVariables.COIN_COUNT++;
                                SoundPlayer.playCoinSound();
                                module.getBlocks().remove(i);
                            }
                        }

                        if (currBlock.getCollisionType() == CollisionType.Enemy) {
                            if (player.isInvulnerable()) {
                                continue;
                            } else {
                                if (checkPreciseCollision(player, currBlock)) {
                                    types.put(currBlock.getCollisionType().ordinal(), currBlock.getCollisionType());
                                    continue;
                                } else {
                                    continue;
                                }
                            }
                        }

                        //If the block's collision is Ground or Wall, check which side the player is hitting it from
                        if (currBlock.getCollisionType() == CollisionType.Wall) {
                            if (player.getX() + player.getWidth() + GlobalVariables.GAME_SPEED - moveCorrection <= currBlock.getX()) {
                                //This checks if the player is on the left of the block and tells him he hit the wall from the side
                                player.setNextToWall(true);
                            } else if (player.getY() + player.getHeight() - GlobalVariables.GRAVITY <= currBlock.getY()) {
                                //This checks if the player is above the block, and tells him he can run on it
                                types.put(CollisionType.Ground.ordinal(), CollisionType.Ground);
                                continue;
                            } else {
                                //This checks if the player is below the block and triggers collision at the middle of the block;
                                types.put(CollisionType.Roof.ordinal(), CollisionType.Roof);
                                continue;
                            }
                        }

                        if (currBlock.getCollisionType() == CollisionType.Ground) {
                            if (player.getY() + player.getHeight() - GlobalVariables.GRAVITY <= currBlock.getY()) {
                                types.put(CollisionType.Ground.ordinal(), CollisionType.Ground);
                                continue;
                            } else {
                                types.put(CollisionType.Roof.ordinal(), CollisionType.Roof);
                                continue;
                            }
                        }

                        if (currBlock.getCollisionType() == CollisionType.Water) {
                            if (player.getY() + player.getHeight() >= currBlock.getY() + 30) {
                                SoundPlayer.playSplashSound();
                            }
                        }
                        //If it is anything other than Ground, just add it
                        types.put(currBlock.getCollisionType().ordinal(), currBlock.getCollisionType());
                    }
                }
            }
        }

        //Always add the default collision, which has the lowest priority
        types.put(CollisionType.None.ordinal(), CollisionType.None);
        //Get the first item in the list, which has the highest priority
        Map.Entry<Integer, CollisionType> entry = types.entrySet().iterator().next();
        return entry.getValue();
    }
}
