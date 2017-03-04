package com.example.kaloyanit.alienrun.Data.base;

import com.example.kaloyanit.alienrun.Models.Player;
import com.orm.SugarContext;
import com.orm.SugarRecord;

/**
 * Created by KaloyanIT on 3/5/2017.
 */

public class Players extends SugarRecord {
    private String name;

    public Players() {

    }

    public Players(String name) {
       this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
