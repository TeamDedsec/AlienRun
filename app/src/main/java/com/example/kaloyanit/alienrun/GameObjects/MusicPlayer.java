package com.example.kaloyanit.alienrun.GameObjects;

import android.media.MediaPlayer;

import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by julian.teofilov on 7/2/2017.
 */

public class MusicPlayer {
    private static MediaPlayer mediaPlayer;

    public static void playBackgroundMusic() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.bgmusic);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
}
