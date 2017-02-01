package com.example.kaloyanit.alienrun.Scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.example.kaloyanit.alienrun.Contracts.IScene;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.Enums.BackgroundType;
import com.example.kaloyanit.alienrun.Enums.BlockSetType;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Enums.PlayerState;
import com.example.kaloyanit.alienrun.Enums.PlayerType;
import com.example.kaloyanit.alienrun.Factories.BackgroundFactory;
import com.example.kaloyanit.alienrun.Factories.LevelModuleFactory;
import com.example.kaloyanit.alienrun.Factories.PlayerFactory;
import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.GameObjects.Block;
import com.example.kaloyanit.alienrun.GameObjects.LevelModule;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class GameplayScene implements IScene {
    private Player player;
    private Background background;
    private Point playerPoint;
    private Bitmap pause;
    private View pauseView;
    LevelModuleFactory moduleFacotry;
    private ArrayList<LevelModule> modules;
    private int frameCounter = 0;

    public GameplayScene() {
        background = BackgroundFactory.createBackground(BackgroundType.Desert);
        pause = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.pause);
        playerPoint = new Point(162, BasicConstants.BG_HEIGHT - 162);
        player = PlayerFactory.createPlayer(PlayerType.Pink, playerPoint.x, playerPoint.y);
        moduleFacotry = new LevelModuleFactory(BlockSetType.Snow);
        modules = new ArrayList<>();
        modules.add(moduleFacotry.getLevelModule());
        modules.add(moduleFacotry.getLevelModule());
    }

    @Override
    public void update() {
        if (player.isAlive()) {
            switch (player.getState()) {
                case Running:
                    switch (checkCollision()) {
                        case None:
                            player.setState(PlayerState.Falling);
                            break;
                        case Water:
                            player.setState(PlayerState.Drowning);
                            player.resetDrownFrames();
                            break;
                        case Wall:
                            player.setState(PlayerState.HitWall);
                            break;
                    }
                case Jumping:
                    switch (checkCollision()) {
                        case Wall:
                            player.setState(PlayerState.HitWall);
                            break;
                    }
                    break;
                case Falling:
                    switch (checkCollision()) {
                        case Water:
                            player.setState(PlayerState.Drowning);
                            break;
                        case Ground:
                            player.setState(PlayerState.Running);
                            player.setJumps(0);
                            break;
                        case Wall:
                            player.setState(PlayerState.HitWall);
                            break;
                    }
                case Drowning:
                    switch (checkCollision()) {
                        case Wall:
                            player.setState(PlayerState.HitWall);
                            break;
                    }
            }

            frameCounter++;
            if(frameCounter == 25) {
                Player.SCORE++;
                frameCounter = 0;
            }

            if(Player.SCORE % 10 == 0) {
                player.increaseSpeed();
            }

            player.update();
            background.update();

            for (int j = 0; j < modules.size(); j++) {
                LevelModule mod = modules.get(j);
                if (mod.getEndX() < -100) {
                    modules.remove(mod);
                } else {
                    mod.update();
                    for (int i = 0; i < mod.getBlocks().size(); i++) {
                        mod.getBlocks().get(i).update();
                    }
                    if (j == modules.size() - 1) {
                        moduleFacotry.updateStartPosition(mod.getEndX());
                        if (mod.getEndX() < BasicConstants.BG_WIDTH) {
                            modules.add(moduleFacotry.getLevelModule());
                        }
                    }
                }
            }
        }
    }

    private CollisionType checkCollision() {
        //Store all found collisions in a sorted list by their priority
        Map<Integer, CollisionType> types = new TreeMap<>();
        for (int j = 0; j < modules.size(); j++) {
            LevelModule module = modules.get(j);
            if (true) {//module.getStartX() <= this.player.getX() && module.getEndX() >= this.player.getX() + this.player.getWidth()) {
                for (int i = 0; i < module.getBlocks().size(); i++) {
                    Block currBlock = module.getBlocks().get(i);
                    //Skip blocks that are not in the width of the player, because they don't have collision anyway
                    if (currBlock.getX() >= this.player.getX() || currBlock.getX() <= this.player.getX() + this.player.getWidth()) {
                        if (Rect.intersects(player.getRectangle(), currBlock.getRectangle())) {
                            //If the block's collision is Ground, check which side the player is hitting it from
                            if (currBlock.getCollisionType() == CollisionType.Ground) {
                                if (this.player.getY() + this.player.getHeight() - GameConstants.GRAVITY <= currBlock.getY()) {
                                    //This checks if the player is above the block, and tells him he can run on it
                                    types.put(CollisionType.Ground.ordinal(), CollisionType.Ground);
                                } else if (this.player.getY() > currBlock.getY() + (currBlock.getHeight() / 2)) {
                                    //This checks if the player is below the block and triggers collision at the middle of the block;
                                    types.put(CollisionType.None.ordinal(), CollisionType.None);
                                } else if (this.player.getX() + this.player.getWidth() >= currBlock.getX()) {
                                    //This checks if the player is on the left side of the block
                                    types.put(CollisionType.Wall.ordinal(), CollisionType.Wall);
                                }
                            }
                            //If it is anything other than Ground, just add it
                            types.put(currBlock.getCollisionType().ordinal(), currBlock.getCollisionType());
                        }
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

    @Override
    public void draw(Canvas canvas) {
        //final float ratio = BasicConstants.SCREEN_WIDTH / (BasicConstants.SCREEN_HEIGHT * 1.0f);
        final float scaleFactorX = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);
        final float scaleFactorY = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);

        final int savedState = canvas.save();
        canvas.scale(scaleFactorX, scaleFactorY);

        background.draw(canvas);
        //canvas.drawBitmap(pause, 10, 0, null);
        player.draw(canvas);

        for (int j = 0; j < modules.size(); j++) {
            LevelModule mod = modules.get(j);
            for (int i = 0; i < mod.getBlocks().size(); i++) {
                mod.getBlocks().get(i).draw(canvas);
            }
        }

        canvas.restoreToCount(savedState);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 2;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        //Sample event
        if (player.isAlive()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    switch (player.getState()) {
                        case Running:
                            player.setState(PlayerState.Jumping);
                            player.resetJump();
                            break;
                        case Jumping:
                            if (player.getJumps() < player.getJumpCount()) {
                                player.resetJump();
                                player.setJumps(player.getJumps() + 1);
                            }
                            break;
                        case Falling:
                            if (player.getJumps() < player.getJumpCount()) {
                                player.setState(PlayerState.Jumping);
                                player.resetJump();
                                player.setJumps(player.getJumps() + 1);
                            }
                            break;
                        case Drowning:
                            if (player.getJumps() < player.getJumpCount()) {
                                player.setState(PlayerState.Jumping);
                                player.resetJump();
                                player.setJumps(player.getJumps() + 1);
                            }
                    }

                    //if(pause.getWidth())
                    //SceneManager.ACTIVE_SCENE = 2;
                }
            }
        }
    }
}
