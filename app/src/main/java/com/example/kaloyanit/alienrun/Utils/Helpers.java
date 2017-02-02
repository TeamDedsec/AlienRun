package com.example.kaloyanit.alienrun.Utils;

import java.util.Random;

/**
 * Created by julian.teofilov on 2/2/2017.
 */

public class Helpers {
    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        int random = rand.nextInt((max - min) + 1) + min;
        return random;
    }
}
