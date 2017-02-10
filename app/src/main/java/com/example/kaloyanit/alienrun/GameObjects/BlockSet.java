package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;

/**
 * Created by julian.teofilov on 31/1/2017.
 */

public class BlockSet {
    private Bitmap[] fullGround = new Bitmap[1];
    private Bitmap[] bottomLeft = new Bitmap[1];
    private Bitmap[] bottomRight = new Bitmap[1];
    private Bitmap[] leftGround = new Bitmap[1];
    private Bitmap[] middleGround = new Bitmap[1];
    private Bitmap[] rightGround = new Bitmap[1];
    private Bitmap[] leftAir = new Bitmap[1];
    private Bitmap[] middleAir = new Bitmap[1];
    private Bitmap[] rightAir = new Bitmap[1];

    public BlockSet(Bitmap fullGround, Bitmap bottomLeft, Bitmap bottomRight, Bitmap leftGround, Bitmap middleGround, Bitmap rightGround, Bitmap leftAir, Bitmap middleAir, Bitmap rightAir) {
        this.fullGround[0] = fullGround;
        this.bottomLeft[0] = bottomLeft;
        this.bottomRight[0] = bottomRight;
        this.leftGround[0] = leftGround;
        this.middleGround[0] = middleGround;
        this.rightGround[0] = rightGround;
        this.leftAir[0] = leftAir;
        this.middleAir[0] = middleAir;
        this.rightAir[0] = rightAir;
    }

    public Bitmap[] getFullGround() { return fullGround; }

    public Bitmap[] getBottomLeft() { return bottomLeft; }

    public Bitmap[] getBottomRight() { return bottomRight; }

    public Bitmap[] getLeftGround() { return leftGround; }

    public Bitmap[] getMiddleGround() { return middleGround; }

    public Bitmap[] getRightGround() { return rightGround; }

    public Bitmap[] getLeftAir() { return leftAir; }

    public Bitmap[] getMiddleAir() {
        return middleAir;
    }

    public Bitmap[] getRightAir() {
        return rightAir;
    }
}
