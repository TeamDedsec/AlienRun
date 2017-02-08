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
import com.example.kaloyanit.alienrun.Factories.EnemyFactory;
import com.example.kaloyanit.alienrun.Factories.LevelModuleFactory;
import com.example.kaloyanit.alienrun.Factories.PlayerFactory;
import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.GameObjects.Enemy;
import com.example.kaloyanit.alienrun.GameObjects.LevelModule;
import com.example.kaloyanit.alienrun.GameObjects.MusicPlayer;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.GameObjects.SoundPlayer;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Utils.Helpers;

import java.util.ArrayList;
import java.util.logging.Level;

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
    public static int score = 0;
    public static int coinCount = 0;
    private int resetCounter = 120;
    private boolean isScoreChecked = false;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GamePlayScene.score = score;
    }

    //TODO: JT: Change collision with obstacle/enemy to something specific, not wall!!! This is more important now, as enemies don't kill you!!
    //TODO: JT: Think of a way to make the player to be able to jump while colliding with a wall

    public GamePlayScene() {
/*        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        music = soundPool.load(BasicConstants.CURRENT_CONTEXT, R.raw.music, 1);
        tryJump = soundPool.load(BasicConstants.CURRENT_CONTEXT, R.raw.tryJump, 1);

        soundPool.play(music, 0.8f, 0.8f, 1, 1, 1.0f);*/
        MusicPlayer.playBackgroundMusic();
        background = BackgroundFactory.createBackground(BackgroundType.Grass);
        pause = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.pause);
        playerPoint = new Point(162, BasicConstants.BG_HEIGHT - 162);
        player = PlayerFactory.createPlayer(GlobalVariables.ACTIVE_PLAYER, playerPoint.x, playerPoint.y - 20);
        moduleFactory = new LevelModuleFactory(BlockSetType.Grass);
        modules = new ArrayList<>();
        enemies = new ArrayList<>();
        modules.add(moduleFactory.getLevelModule(0));
        modules.add(moduleFactory.getLevelModule(4));
        modules.add(moduleFactory.getLevelModule(1));
        modules.add(moduleFactory.getLevelModule(2));
    }

    @Override
    public void update() {
        if (player.isInBounds()) {
            player.update();
            background.update();
            moduleFactory.update();

            for (int j = 0; j < modules.size(); j++) {
                LevelModule mod = modules.get(j);
                mod.update();
                if (mod.getEndX() < mod.getLength() * -1) {
                    modules.remove(mod);
                    score++;
                }

                if (j == modules.size() - 1) {
                    if (mod.getEndX() < BasicConstants.BG_WIDTH) {
                        modules.add(moduleFactory.getLevelModule());
                    }
                }
            }

            if (!player.isInvulnerable()) {
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(0);
                    enemy.update();
                    if (enemy.getX() < -100) {
                        enemies.remove(i);
                    } else {
                        if (Helpers.checkPreciseCollision(player, enemy)) {
                            player.hitIntoEnemy();
                        }
                    }
                }
            }

            player.updateState(Helpers.checkCollision(player, modules));
            //TODO: JT: think of a better way to spawn enemies
            if (score % 5 == 0 && score > 0 && !isScoreChecked) {
                int rand = Helpers.getRandomNumber(GameConstants.BLOCK_HEIGHT * 2, BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT * 2);
                enemies.add(EnemyFactory.createEnemy(BasicConstants.BG_WIDTH, rand));
                if (score % 10 == 0) {
                    this.increaseSpeed();
                }

                if (score % 40 == 0) {
                    background.setImage(BackgroundFactory.getBackgroundImage());
                    moduleFactory.changeBlockType();
                }
                isScoreChecked = true;
            } else if (score % 5 != 0) {
                isScoreChecked = false;
            }
        } else {
            resetCounter--;
            if (resetCounter <= 0) {
                SceneManager.resetGame();
            }
        }
    }

    private void increaseSpeed() {
        GlobalVariables.GAME_SPEED -= 2;
        GlobalVariables.GRAVITY += 2;
        GlobalVariables.JUMP_VELOCITY -= 2;
        if (GlobalVariables.DELAY > 0) {
            GlobalVariables.DELAY -= 2;
        }
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

        for (int j = 0; j < modules.size(); j++) {
            LevelModule mod = modules.get(j);
            for (int i = 0; i < mod.getBlocks().size(); i++) {
                if (mod.getBlocks().get(i).getCollisionType() != CollisionType.Water)
                mod.getBlocks().get(i).draw(canvas);
            }
        }

        player.draw(canvas);

        for (int i = 0; i < modules.size(); i++) {
            LevelModule mod = modules.get(i);
            for (int j = 0; j < mod.getBlocks().size(); j++) {
                if (mod.getBlocks().get(j).getCollisionType() == CollisionType.Water) {
                    mod.getBlocks().get(j).draw(canvas);
                }
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
        if (player.isInBounds() && x > BasicConstants.BG_WIDTH / 2 && y > BasicConstants.BG_HEIGHT / 2) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    if (player.isInBounds()) {
                        if (player.tryJump()) {
                            SoundPlayer.playJumpSound();
                        }
                    }

                    //if(pause.getWidth())
                    //SceneManager.ACTIVE_SCENE = 2;
                }
            }
        }
    }

    //TODO: JT: Fix this shit and extract it
    private boolean checkTouchCollision(Rect a, Rect b) {
        boolean shit = false;
        shit = Rect.intersects(a, b);
        return shit;
    }
}
