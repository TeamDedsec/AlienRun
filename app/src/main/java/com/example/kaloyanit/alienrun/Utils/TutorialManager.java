package com.example.kaloyanit.alienrun.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Enums.TutorialType;
import com.example.kaloyanit.alienrun.R;

/**
 * Created by Chet on 3.3.2017 г..
 */

public class TutorialManager {
    private static Bitmap tutorialImage;

    public static TutorialType activeTutorial;
    public static boolean isTutorialActive = false;
    public static boolean isJumpTutorialShown = false;
    public static boolean isDoubleJumpTutorialShown = false;
    public static boolean isBombTutorialShown = false;
    public static boolean isPinchTutorialShown = false;
    public static boolean isHeartTutorialShown = false;
    public static boolean isStarTutorialShown = false;
    public static boolean isSignTutorialShown = false;
    public static final int x = 200;
    public static final int y = 200;
    public static final int height = 300;
    public static final int width = 500;

    private static void showJumpTutorial(Canvas canvas) {
        tutorialImage = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.tutorial_jump);
        canvas.drawBitmap(tutorialImage, x, y, null);
    }

    private static void showDoubleJumpTutorial(Canvas canvas) {
        tutorialImage = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.tutorial_double_jump);
        canvas.drawBitmap(tutorialImage, x, y, null);
    }

    private static void showBombTutorial(Canvas canvas) {
        tutorialImage = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.tutorial_bomb);
        canvas.drawBitmap(tutorialImage, x, y, null);
    }

    private static void showPinchTutorial(Canvas canvas) {
        tutorialImage = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.tutorial_pinch);
        canvas.drawBitmap(tutorialImage, x, y, null);
    }

    private static void showHeartTutorial(Canvas canvas) {
        tutorialImage = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.tutorial_heart);
        canvas.drawBitmap(tutorialImage, x, y, null);
    }

    private static void showStarTutorial(Canvas canvas) {
        tutorialImage = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.tutorial_star);
        canvas.drawBitmap(tutorialImage, x, y, null);
    }

    private static void showSignTutorial(Canvas canvas) {
        tutorialImage = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.tutorial_sign);
        canvas.drawBitmap(tutorialImage, x, y, null);
    }

    public static void showTutorial(Canvas canvas) {
        switch (activeTutorial) {
            case Jump:
                showJumpTutorial(canvas);
                break;
            case DoubleJump:
                showDoubleJumpTutorial(canvas);
                break;
            case Bomb:
                showBombTutorial(canvas);
                break;
            case Pinch:
                showPinchTutorial(canvas);
                break;
            case Heart:
                showHeartTutorial(canvas);
                break;
            case Star:
                showStarTutorial(canvas);
                break;
            case Sign:
                showSignTutorial(canvas);
                break;
            default:
                throw new RuntimeException();
        }
    }

    public static void hideTutorial() {
        isTutorialActive = false;

        switch (activeTutorial) {
            case Jump:
                isJumpTutorialShown = true;
                break;
            case DoubleJump:
                isDoubleJumpTutorialShown = true;
                break;
            case Bomb:
                isBombTutorialShown = true;
                break;
            case Pinch:
                isPinchTutorialShown = true;
                break;
            case Heart:
                isHeartTutorialShown = true;
                break;
            case Star:
                isStarTutorialShown = true;
                break;
            case Sign:
                isSignTutorialShown = true;
                break;
            default:
                throw new RuntimeException();
        }
    }
}

