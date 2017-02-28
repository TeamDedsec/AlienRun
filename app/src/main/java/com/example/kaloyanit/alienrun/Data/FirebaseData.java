package com.example.kaloyanit.alienrun.Data;

import com.example.kaloyanit.alienrun.Data.base.BaseData;
import com.example.kaloyanit.alienrun.Models.ModelBase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.Observable;

/**
 * Created by KaloyanIT on 2/20/2017.
 */

public class FirebaseData<T extends ModelBase> {
    private DatabaseReference database;

    public FirebaseData() {
        this.database = FirebaseDatabase.getInstance().getReference();
    }
}
