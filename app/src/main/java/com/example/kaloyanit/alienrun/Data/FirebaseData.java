package com.example.kaloyanit.alienrun.Data;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.kaloyanit.alienrun.Data.base.BaseData;
import com.example.kaloyanit.alienrun.Models.ModelBase;
import com.example.kaloyanit.alienrun.Models.User;
import com.google.common.base.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by KaloyanIT on 2/20/2017.
 */

public class FirebaseData {
    private ArrayList<User> users;
    private FirebaseDatabase database;
    private DatabaseReference refDb;
    private FirebaseUser user;
    private FirebaseAuth auth;

    public FirebaseData() {
        this.database = FirebaseDatabase.getInstance();
        this.refDb = database.getReference();
        this.auth = FirebaseAuth.getInstance();
        this.user = auth.getCurrentUser();
        this.users = new ArrayList<>();
        int a = 5;
    }

    public void addUser(int score) {
        if(user != null) {
            String userId = this.user.getUid();
            String fName = this.user.getDisplayName();
            User user = new User(fName, score);
            refDb.child("users").child(userId).setValue(user);
        } else {
            //TODO: Add exception
            return;
        }

    }

    public void updateUserScore(String userId, String name, int score) {
        refDb.child("users").child(userId).setValue(null);
        User user = new User(name, score);

        refDb.child("users").child(userId).setValue(user);
    }

    public ArrayList<User> getRank() {
        return this.users;
    }

    public void getData() {
        refDb.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                users.add(user);
                System.out.println("USEERRR");
                System.out.println(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Failed");
            }
        });
    }
}
