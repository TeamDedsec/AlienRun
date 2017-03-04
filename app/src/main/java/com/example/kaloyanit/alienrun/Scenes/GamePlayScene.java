package com.example.kaloyanit.alienrun.Scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.example.kaloyanit.alienrun.Contracts.IScene;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.Enums.BackgroundType;
import com.example.kaloyanit.alienrun.Enums.BlockSetType;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Enums.PowerUpType;
import com.example.kaloyanit.alienrun.Enums.TutorialType;
import com.example.kaloyanit.alienrun.Factories.BackgroundFactory;
import com.example.kaloyanit.alienrun.Factories.EnemyFactory;
import com.example.kaloyanit.alienrun.Factories.LevelModuleFactory;
import com.example.kaloyanit.alienrun.Factories.PlayerFactory;
import com.example.kaloyanit.alienrun.Factories.PowerUpFactory;
import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.GameObjects.Bomb;
import com.example.kaloyanit.alienrun.GameObjects.Enemy;
import com.example.kaloyanit.alienrun.GameObjects.Explosion;
import com.example.kaloyanit.alienrun.GameObjects.LevelModule;
import com.example.kaloyanit.alienrun.SoundPlayers.MusicPlayer;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.GameObjects.PowerUps.PowerUp;
import com.example.kaloyanit.alienrun.SoundPlayers.SoundPlayer;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Utils.Helpers;
import com.example.kaloyanit.alienrun.Utils.ScaleDetector;
import com.example.kaloyanit.alienrun.Utils.TutorialManager;

import java.util.ArrayList;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class GamePlayScene implements IScene {
    private float touchX;
    private float touchY;
    private Player player;
    private Background background;
    private Point playerPoint;
    private Bitmap jumpButton;
    private Bitmap jumpButtonHighlighted;
    private Bitmap pause;
    private View pauseView;
    private LevelModuleFactory moduleFactory;
    private ArrayList<LevelModule> modules;
    private ArrayList<PowerUp> powerUps;
    private int frameCounter = 0;
    private int modulesPassed;
    private int resetCounter = 120;
    private boolean isScoreChecked = false;
    private boolean isJumpButtonPressed = false;
    private Enemy enemy = null;
    private Bomb bomb = null;
    private Explosion explosion = null;
    private Paint paint;
    private int speedBeforePause;
    private boolean isPaused = false;


    //TODO: JT: Change collision with obstacle/enemy to something specific, not wall!!! This is more important now, as enemy don't kill you!!
    //TODO: JT: Think of a way to make the player to be able to jump while colliding with a wall

    public GamePlayScene() {
/*        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        music = soundPool.load(BasicConstants.CURRENT_CONTEXT, R.raw.music, 1);
        tryJump = soundPool.load(BasicConstants.CURRENT_CONTEXT, R.raw.tryJump, 1);

        soundPool.play(music, 0.8f, 0.8f, 1, 1, 1.0f);*/
        MusicPlayer.playBackgroundMusic();
        background = BackgroundFactory.createBackground(BackgroundType.Grass);
        paint = new Paint();
        if (GlobalVariables.GAMES_PLAYED > 15) {
            paint.setAlpha(0);
        } else if (GlobalVariables.GAMES_PLAYED > 5) {
            paint.setAlpha(100);
        } else {
            paint.setAlpha(200);
        }
        jumpButton = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.jump_button);
        jumpButtonHighlighted = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.jump_button_highlighted);
        jumpButton = Bitmap.createScaledBitmap(jumpButton, 100, 100, false);
        jumpButtonHighlighted = Bitmap.createScaledBitmap(jumpButtonHighlighted, 100, 100, false);
        //pause = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.pause);
        playerPoint = new Point(162, BasicConstants.BG_HEIGHT - 162);
        player = PlayerFactory.createPlayer(GlobalVariables.ACTIVE_PLAYER, playerPoint.x, playerPoint.y - 20);
        moduleFactory = new LevelModuleFactory(BlockSetType.Grass);
        modules = new ArrayList<>();
        powerUps = new ArrayList<>();
        modules.add(moduleFactory.getLevelModule(0));
        modules.add(moduleFactory.getLevelModule(4));
        modules.add(moduleFactory.getLevelModule(1));
        modules.add(moduleFactory.getLevelModule(2));
    }

    @Override
    public void update() {
        if (!isPaused) {
            if (!TutorialManager.isJumpTutorialShown && GlobalVariables.startFrameCount <= 0 && !TutorialManager.isTutorialActive) {
                TutorialManager.activeTutorial = TutorialType.Jump;
                pauseGame();
            } else {
                GlobalVariables.startFrameCount--;
            }

            if (GlobalVariables.isFirstJumpActive) {
                GlobalVariables.jumpFrameCount--;
                if (GlobalVariables.jumpFrameCount <= 0 && !TutorialManager.isTutorialActive) {
                    TutorialManager.activeTutorial = TutorialType.DoubleJump;
                    pauseGame();
                }
            }

            if (player.isInBounds()) {
                player.update();
                background.update();
                moduleFactory.update();

                if (explosion != null) {
                    explosion.update();
                    if (explosion.isFinished()) {
                        explosion = null;
                    }
                }

                if (bomb != null) {
                    bomb.update();
                    if (enemy != null) {
                        if (Rect.intersects(bomb.getRectangle(), enemy.getRectangle())) {
                            if (explosion == null) {
                                explosion = new Explosion(bomb.getX() + bomb.getWidth(), bomb.getY() + bomb.getHeight() / 2);
                                SoundPlayer.playExplosionSound();
                            }
                            bomb = null;
                            enemy = null;
                            GlobalVariables.SCORE += 5;
                        }
                    }
                    if (bomb != null && (bomb.getX() > BasicConstants.BG_WIDTH || bomb.getY() > BasicConstants.BG_HEIGHT)) {
                        bomb = null;
                    }
                }

                for (int i = 0; i < powerUps.size(); i++) {
                    PowerUp currPower = powerUps.get(i);
                    currPower.update();

                    if (GlobalVariables.isFirstHeart && currPower.type == PowerUpType.ExtraLife && currPower.getX() < BasicConstants.BG_WIDTH - currPower.getWidth() && !TutorialManager.isTutorialActive) {
                        TutorialManager.activeTutorial = TutorialType.Heart;
                        TutorialManager.isTutorialActive = true;
                        pauseGame();
                    }
                    if (GlobalVariables.isFirstSign && currPower.type == PowerUpType.Mover && currPower.getX() < BasicConstants.BG_WIDTH - currPower.getWidth() &&!TutorialManager.isTutorialActive) {
                        TutorialManager.activeTutorial = TutorialType.Sign;
                        TutorialManager.isTutorialActive = true;
                        pauseGame();
                    }
                    if (GlobalVariables.isFirstStar && currPower.type == PowerUpType.Invincibility && currPower.getX() < BasicConstants.BG_WIDTH - currPower.getWidth() &&!TutorialManager.isTutorialActive) {
                        TutorialManager.activeTutorial = TutorialType.Star;
                        TutorialManager.isTutorialActive = true;
                        pauseGame();
                    }

                    if (player.getRectangle().intersect(currPower.getRectangle())) {
                        currPower.executeEffect(player);
                        powerUps.remove(currPower);
                    }
                }

                for (int j = 0; j < modules.size(); j++) {
                    LevelModule mod = modules.get(j);
                    mod.update();
                    if (GlobalVariables.isFirstPinch && mod.isPinchRequired && mod.getStartX() < BasicConstants.BG_WIDTH && !TutorialManager.isTutorialActive) {
                        TutorialManager.activeTutorial = TutorialType.Pinch;
                        TutorialManager.isTutorialActive = true;
                        pauseGame();
                    }
                    if (mod.getEndX() < mod.getLength() * -1) {
                        modules.remove(mod);
                        modulesPassed++;
                        GlobalVariables.SCORE++;
                    }

                    if (j == modules.size() - 1) {
                        if (mod.getEndX() < BasicConstants.BG_WIDTH) {
                            LevelModule newModule = moduleFactory.getLevelModule();
                            modules.add(newModule);
                            int rng = Helpers.getRandomNumber(0, 3);
                            if (rng == 0) {
                                if (player.getLives() == 1 && player.getX() >= 300) {
                                    powerUps.add(PowerUpFactory.createPowerUp(PowerUpType.Invincibility, newModule.getStartX(), BasicConstants.BG_HEIGHT - (GameConstants.BLOCK_HEIGHT * 2)));
                                } else if (player.getLives() <= 1 && player.getX() <= 300) {
                                    powerUps.add(PowerUpFactory.createPowerUp(newModule.getStartX(), BasicConstants.BG_HEIGHT - (GameConstants.BLOCK_HEIGHT * 2)));
                                } else if (player.getLives() <= 1) {
                                    powerUps.add(PowerUpFactory.createPowerUp(PowerUpType.ExtraLife, newModule.getStartX(), BasicConstants.BG_HEIGHT - (GameConstants.BLOCK_HEIGHT * 2)));
                                } else if (player.getX() <= 300) {
                                    powerUps.add(PowerUpFactory.createPowerUp(PowerUpType.Mover, newModule.getStartX(), BasicConstants.BG_HEIGHT - (GameConstants.BLOCK_HEIGHT * 2)));
                                }
                            }
                        }
                    }
                }

                if (enemy != null) {
                    enemy.update();
                    if (GlobalVariables.isFirstEnemy && enemy.getX() < BasicConstants.BG_WIDTH - enemy.getWidth() - 100 && !TutorialManager.isTutorialActive) {
                        TutorialManager.activeTutorial = TutorialType.Bomb;
                        TutorialManager.isTutorialActive = true;
                        pauseGame();
                    }
                    if (enemy.getX() < -100) {
                        enemy = null;
                    } else {
                        if (!player.isInvulnerable() && !player.isFlying() && Helpers.checkPreciseCollision(player, enemy)) {
                            player.hitIntoEnemy();
                        }
                    }
                }

                player.updateState(Helpers.checkCollision(player, modules));

                //TODO: JT: think of a better way to spawn enemy
                if (modulesPassed % 5 == 0 && modulesPassed > 0 && !isScoreChecked) {
                    int rand = Helpers.getRandomNumber(BasicConstants.SCREEN_HEIGHT / 3, BasicConstants.BG_HEIGHT - GameConstants.BLOCK_HEIGHT * 2);
                    enemy = EnemyFactory.createEnemy(BasicConstants.BG_WIDTH, rand);
                    if (modulesPassed % 10 == 0) {
                        this.increaseSpeed();
                    }

                    if (modulesPassed % 15 == 0) {
                        background.setImage(BackgroundFactory.getBackgroundImage());
                        moduleFactory.changeBlockType();
                    }
                    isScoreChecked = true;
                } else if (modulesPassed % 5 != 0) {
                    isScoreChecked = false;
                }
            } else {
                GlobalVariables.GAME_SPEED = 0;
                if (bomb != null) {
                    bomb.update();
                }
                if (enemy != null) {
                    enemy.update();
                }
                if (explosion != null) {
                    explosion.update();
                }
                resetCounter--;
                if (resetCounter <= 0) {
                    SceneManager.resetGame();
                }
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

        for (int i = 0; i < powerUps.size(); i++) {
            powerUps.get(i).draw(canvas);
        }

        if (enemy != null) {
            enemy.draw(canvas);
        }

        if (bomb != null) {
            bomb.draw(canvas);
        }

        if (explosion != null) {
            explosion.draw(canvas);
        }

        if (GlobalVariables.GAMES_PLAYED > 15) {
        } else if (GlobalVariables.GAMES_PLAYED > 5 && modulesPassed == 5) {
            paint.setAlpha(0);
        } else if (GlobalVariables.GAMES_PLAYED < 5 && modulesPassed == 5) {
            paint.setAlpha(100);
        } else if (GlobalVariables.GAMES_PLAYED < 5 && modulesPassed == 15) {
            paint.setAlpha(0);
        }

        if (isJumpButtonPressed) {
            canvas.drawBitmap(jumpButtonHighlighted, (BasicConstants.SCREEN_WIDTH / GlobalVariables.xRATIO) - 480, (BasicConstants.SCREEN_HEIGHT / GlobalVariables.yRATIO) - 120, paint);
        } else {
            canvas.drawBitmap(jumpButton, (BasicConstants.SCREEN_WIDTH / GlobalVariables.xRATIO) - 480, (BasicConstants.SCREEN_HEIGHT / GlobalVariables.yRATIO) - 120, paint);
        }
        if (isPaused) {
            TutorialManager.showTutorial(canvas);
        }

        for (int i = 1; i < player.getLives(); i++) {
            canvas.drawBitmap(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.heart), (50 * i) + 20, BasicConstants.BG_HEIGHT - 50, null);
        }

        canvas.restoreToCount(savedState);
    }

    public void pauseGame() {
        speedBeforePause = GlobalVariables.GAME_SPEED;
        GlobalVariables.GAME_SPEED = 0;
        isPaused = true;
    }

    public void unPauseGame() {
        GlobalVariables.GAME_SPEED = speedBeforePause;
        isPaused = false;
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 2;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if (!isPaused) {
            ScaleDetector.scaleDetector.onTouchEvent(event);
            GlobalVariables.xRATIO = BasicConstants.SCREEN_WIDTH / (BasicConstants.BG_WIDTH * 1.0f);
            GlobalVariables.yRATIO = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);

            float x = event.getX();
            float y = event.getY();

            if (player.isInBounds()) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchX = x;
                        touchY = y;

                        if (x > (BasicConstants.SCREEN_WIDTH / 3) * 2 && y > (BasicConstants.SCREEN_HEIGHT / 3) * 2) {
                            isJumpButtonPressed = true;
                            if (player.tryJump()) {
                                if (GlobalVariables.isFirstJump) {
                                    GlobalVariables.isFirstJumpActive = true;
                                }
                                SoundPlayer.playJumpSound();
                            }
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        isJumpButtonPressed = false;
                        if (ScaleDetector.scaleFactor > 1) {
                            player.becomeBig();
                            ScaleDetector.scaleFactor = 1.0f;
                        } else if (ScaleDetector.scaleFactor < 1) {
                            player.becomeSmall();
                            ScaleDetector.scaleFactor = 1.0f;
                        } else {
                            if (touchX < (BasicConstants.SCREEN_WIDTH / 3) * 2 && touchX < x && bomb == null && player.isAlive()) {
                                bomb = new Bomb(0, (int) (touchY / GlobalVariables.yRATIO));
                            }
                        }
                        break;
                }
            }
        } else {
            if (event.getX() >= TutorialManager.x &&
                    event.getX() <= TutorialManager.x + TutorialManager.width &&
                    event.getY() >= TutorialManager.y &&
                    event.getX() <= TutorialManager.y + TutorialManager.height) {

                if (TutorialManager.activeTutorial == TutorialType.Bomb) {
                    GlobalVariables.isFirstEnemy = false;
                }

                if (TutorialManager.activeTutorial == TutorialType.DoubleJump) {
                    GlobalVariables.isFirstJump = false;
                    GlobalVariables.isFirstJumpActive = false;
                }

                if (TutorialManager.activeTutorial == TutorialType.Pinch) {
                    GlobalVariables.isFirstPinch = false;
                }

                if (TutorialManager.activeTutorial == TutorialType.Heart) {
                    GlobalVariables.isFirstHeart = false;
                }

                if (TutorialManager.activeTutorial == TutorialType.Star) {
                    GlobalVariables.isFirstStar = false;
                }

                if (TutorialManager.activeTutorial == TutorialType.Sign) {
                    GlobalVariables.isFirstSign = false;
                }

                TutorialManager.hideTutorial();
                unPauseGame();
            }
        }
    }
}
