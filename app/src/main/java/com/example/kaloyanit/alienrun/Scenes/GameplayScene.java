package com.example.kaloyanit.alienrun.Scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore;
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
import com.example.kaloyanit.alienrun.Factories.EnemyFactory;
import com.example.kaloyanit.alienrun.Factories.LevelModuleFactory;
import com.example.kaloyanit.alienrun.Factories.PlayerFactory;
import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.GameObjects.Block;
import com.example.kaloyanit.alienrun.GameObjects.Enemy;
import com.example.kaloyanit.alienrun.GameObjects.GameObject;
import com.example.kaloyanit.alienrun.GameObjects.LevelModule;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GameGlobalNumbers;
import com.example.kaloyanit.alienrun.Utils.Helpers;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class GamePlayScene implements IScene {
    private float touchX;
    private float touchY;
    private Player player;
    private Background background;
    private Point playerPoint;
    private Bitmap pause;
    private View pauseView;
    private LevelModuleFactory moduleFactory;
    private ArrayList<LevelModule> modules;
    private ArrayList<Enemy> enemies;
    private int frameCounter = 0;
    private static int score = 0;
    private int coinCount = 0;
    private int resetCounter = 120;
    private MediaPlayer jumpSound;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GamePlayScene.score = score;
    }

    //TODO: JT: Make the game reset upon dying
    //TODO: JT: Add flying enemies
    //TODO: JT: Make player not die upon colliding with a wall
    //TODO: JT: Change collision with obstacle/enemy to something specific, not wall

    public GamePlayScene() {

        this.jumpSound = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.jump);

        background = BackgroundFactory.createBackground(BackgroundType.Grass);
        pause = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.pause);
        playerPoint = new Point(162, BasicConstants.BG_HEIGHT - 162);
        player = PlayerFactory.createPlayer(PlayerType.Green, playerPoint.x, playerPoint.y - 20);
        moduleFactory = new LevelModuleFactory(BlockSetType.Grass);
        modules = new ArrayList<>();
        enemies = new ArrayList<>();
        modules.add(moduleFactory.getLevelModule(0));
        modules.add(moduleFactory.getLevelModule(4));
        modules.add(moduleFactory.getLevelModule(5));
    }

    @Override
    public void update() {
        if (player.isAlive()) {
            player.update();
            background.update();
            moduleFactory.update();

            for (int j = 0; j < modules.size(); j++) {
                LevelModule mod = modules.get(j);
                mod.update();
                if (mod.getEndX() < mod.getLength() * -1) {
                    modules.remove(mod);
                }

                if (j == modules.size() - 1) {
                    if (mod.getEndX() < BasicConstants.BG_WIDTH) {
                        modules.add(moduleFactory.getLevelModule());
                    }
                }
            }


            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(0);
                enemy.update();
                if (enemy.getX() < -100) {
                    enemies.remove(i);
                } else {
                    if (checkPreciseCollision(player, enemy)) {
                        player.setState(PlayerState.HitWall);
                    }
                }
            }

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
                        case Enemy:
                            player.setState(PlayerState.HitWall);
                            break;
                    }
                    break;
                case Jumping:
                    switch (checkCollision()) {
                        case Wall:
                            player.setState(PlayerState.HitWall);
                            break;
                        case Enemy:
                            player.setState(PlayerState.HitWall);
                            break;
                    }
                    break;
                case HighPoint:
                    switch (checkCollision()) {
                        case Wall:
                            player.setState(PlayerState.HitWall);
                            break;
                        case Enemy:
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
                        case Enemy:
                            player.setState(PlayerState.HitWall);
                            break;
                    }
                    break;
                case Drowning:
                    switch (checkCollision()) {
                        case Wall:
                            player.setState(PlayerState.HitWall);
                            break;
                        case Enemy:
                            player.setState(PlayerState.HitWall);
                            break;
                    }
                    break;
            }

            frameCounter++;
            if (frameCounter == 25) {
                this.score++;
                frameCounter = 0;

                //TODO: JT: think of a better way to spawn enemies
                if (this.score % 5 == 0) {
                    int rand = Helpers.getRandomNumber(GameConstants.BLOCK_HEIGHT * 2, BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT * 2);
                    enemies.add(EnemyFactory.createEnemy(BasicConstants.BG_WIDTH, rand));
                }

                if (this.score % 10 == 0) {
                    this.increaseSpeed();
                }

                if (this.score % 40 == 0) {
                    background = BackgroundFactory.createBackground(BackgroundType.Mushroom);
                    moduleFactory.changeBlockType();
                }
            }
        } else {
            resetCounter--;
            if (resetCounter <= 0) {
                SceneManager.resetGame();
            }
        }
    }

    private void increaseSpeed() {
        GameGlobalNumbers.GAME_SPEED -= 2;
        GameGlobalNumbers.GRAVITY += 2;
        GameGlobalNumbers.JUMP_VELOCITY -= 2;
        if (GameGlobalNumbers.DELAY > 0) {
            GameGlobalNumbers.DELAY -= 2;
        }
    }

    private boolean checkCollision(GameObject a, GameObject b) {
        return Rect.intersects(a.getRectangle(), b.getRectangle());
    }

    private boolean checkPreciseCollision(GameObject a, GameObject b) {
        return Rect.intersects(a.getRectangle(), new Rect(b.getX() + 20, b.getY() + 20, b.getX() + b.getWidth() - 20, b.getY() + b.getHeight() - 20));
    }

    private CollisionType checkCollision() {
        //TODO: JT: Update and refactor collision
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
                                coinCount++;
                                module.getBlocks().remove(i);
                            }
                        }
                        if (currBlock.getCollisionType() == CollisionType.Enemy) {
                            //Lower the collision radius if it's an enemy
                            if (checkCollision(player, currBlock)) {
                                types.put(currBlock.getCollisionType().ordinal(), currBlock.getCollisionType());
                                continue;
                            }
                            continue;
                        }
                        //If the block's collision is Ground, check which side the player is hitting it from
                        if (currBlock.getCollisionType() == CollisionType.Ground) {
                            if (this.player.getY() + this.player.getHeight() - GameGlobalNumbers.GRAVITY <= currBlock.getY()) {
                                //This checks if the player is above the block, and tells him he can run on it
                                types.put(CollisionType.Ground.ordinal(), CollisionType.Ground);
                            } else if (this.player.getY() > currBlock.getY() + (currBlock.getHeight())) {
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

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(0).draw(canvas);
        }

        canvas.restoreToCount(savedState);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 2;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                //TODO: JT: Fix this shit
                int length;
                int height;

                if (x < touchX) {
                    float temp = x;
                    x = touchX;
                    touchX = temp;
                }

                if (y < touchY) {
                    float temp = y;
                    y = touchY;
                    touchY = temp;
                }

                length = (int) (x - touchX);
                height = (int) (y - touchY);

                Rect shit = new Rect((int) touchX, (int) touchY, (int) touchX + length, (int) touchY + height);
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    if (checkTouchCollision(shit, enemy.getRectangle())) {//; Rect.intersects(shit, enemy.getRectangle())) {
                        enemies.remove(i);
                        this.score += 5;
                    }
                }
        }


        //Sample event
        if (player.isAlive() && x < BasicConstants.BG_WIDTH / 2 && y > BasicConstants.BG_HEIGHT / 2) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    switch (player.getState()) {
                        case Running:
                            player.setState(PlayerState.Jumping);

                            playSound();

                            player.resetJump();
                            break;
                        case Jumping:
                            if (player.getJumps() < player.getJumpCount()) {

                                playSound();

                                player.resetJump();
                                player.setJumps(player.getJumps() + 1);
                            }
                            break;
                        case HighPoint:
                            if (player.getJumps() < player.getJumpCount()) {
                                player.setState(PlayerState.Jumping);

                                playSound();

                                player.resetJump();
                                player.setJumps(player.getJumps() + 1);
                            }
                            break;
                        case Falling:
                            if (player.getJumps() < player.getJumpCount()) {
                                player.setState(PlayerState.Jumping);

                                playSound();

                                player.resetJump();
                                player.setJumps(player.getJumps() + 1);
                            }
                            break;
                        case Drowning:
                            if (player.getJumps() < player.getJumpCount()) {
                                player.setState(PlayerState.Jumping);

                                playSound();

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

    private boolean checkTouchCollision(Rect a, Rect b) {
        boolean shit = false;
        shit = Rect.intersects(a, b);
        return shit;
    }

    private void playSound() {
        // This makes the player not jump sometimes
        if (jumpSound.isPlaying()) {
            jumpSound = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.jump);
        }
        jumpSound.start();
    }
}
