package com.example.kaloyanit.alienrun.Factories;

import android.graphics.BitmapFactory;

import com.example.kaloyanit.alienrun.Enums.BlockSetType;
import com.example.kaloyanit.alienrun.Enums.BlockType;
import com.example.kaloyanit.alienrun.GameObjects.BlockSet;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by julian.teofilov on 31/1/2017.
 */

public class BlockSetFactory {
    public static BlockSet createBlockSet(BlockSetType type) {
        switch (type) {
            case Grass:
                return new BlockSet(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grassfull),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grassbottomleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grassbottomright),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grassleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grassmid),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grassright),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grasshalfleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grasshalfmid),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grasshalfright));
            case Sand:
                return new BlockSet(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.sandfull),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.sandbottomleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.sandbottomright),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.sandleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.sandmid),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.sandright),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.sandhalfleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.sandhalfmid),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.sandhalfirght));
            case Snow:
                return new BlockSet(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.snowfull),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.snowbottomleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.snowbottomright),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.snowleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.snowmid),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.snowright),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.snowhalfleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.snowhalfmid),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.snowhalfright));
            case Rock:
                return new BlockSet(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.dirtfull),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.dirtbottomleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.dirtbottomright),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.dirtleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.dirtmid),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.dirtright),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.dirthalfleft),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.dirthalfmid),
                        BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.dirthalfright));
            default:
                throw new RuntimeException();
        }
    }
}
