package com.example.kaloyanit.alienrun.Factories;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Enums.EnemyType;
import com.example.kaloyanit.alienrun.GameObjects.Block;
import com.example.kaloyanit.alienrun.GameObjects.Enemy;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.Helpers;

/**
 * Created by julian.teofilov on 3/2/2017.
 */

public class EnemyFactory {
    public static Enemy createEnemy(int x, int y) {
        int rand = Helpers.getRandomNumber(0, 1);
        switch (rand) {
            case 0:
                return createEnemy(EnemyType.Bat, x, y);
            case 1:
                return createEnemy(EnemyType.Fly, x, y);
            case 2:
                return createEnemy(EnemyType.Fish, x, y);
            case 3:
                return createEnemy(EnemyType.Piranha, x, y);
            case 4:
                return createEnemy(EnemyType.Bee,x, y);
            case 5:
                return createEnemy(EnemyType.Snail, x, y);
            case 6:
                return createEnemy(EnemyType.Worm, x, y);
            case 7:
                return createEnemy(EnemyType.Snake,x, y);
            default:
                throw new RuntimeException();
        }
    }

    public static Enemy createEnemy(EnemyType type, int x, int y) {
        Bitmap[] anim;
        int randSpeed = (Helpers.getRandomNumber(5, 10)) * -1;

        switch (type) {
            case Bat:
                anim = new Bitmap[2];
                anim[0] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.bat);
                anim[1] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.bat_fly);
                return new Enemy(anim, 2, "Bat", x, y, 70, 47, randSpeed, 9, 10);
            case Fly:
                anim = new Bitmap[2];
                anim[0] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.fly);
                anim[1] = BitmapFactory.decodeResource(
                        BasicConstants.CURRENT_CONTEXT.getResources(),
                        R.drawable.fly_fly);
                return new Enemy(anim, 2, "Fly", x, y, 57, 45, randSpeed, 5, 6);
            default:
                throw new RuntimeException();
        }
    }
}
