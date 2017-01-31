package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;

/**
 * Created by julian.teofilov on 31/1/2017.
 */

public class BlockSet {
    private Bitmap leftGround;
    private Bitmap middleGround;
    private Bitmap rightGround;
    private Bitmap leftAir;
    private Bitmap middleAir;
    private Bitmap rightAir;

    public BlockSet(Bitmap leftGround, Bitmap middleGround, Bitmap rightGround, Bitmap leftAir, Bitmap middleAir, Bitmap rightAir) {
        this.leftGround = leftGround;
        this.middleGround = middleGround;
        this.rightGround = rightGround;
        this.leftAir = leftAir;
        this.middleAir = middleAir;
        this.rightAir = rightAir;
    }

    public Bitmap getLeftGround() {
        return leftGround;
    }

    public Bitmap getMiddleGround() {
        return middleGround;
    }

    public Bitmap getRightGround() { return rightGround; }

    public Bitmap getLeftAir() {
        return leftAir;
    }

    public Bitmap getMiddleAir() {
        return middleAir;
    }

    public Bitmap getRightAir() {
        return rightAir;
    }
}
