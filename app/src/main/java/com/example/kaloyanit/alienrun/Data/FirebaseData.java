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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;

/**
 * Created by KaloyanIT on 2/20/2017.
 */

public class FirebaseData {
    public static ArrayList<User> users = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference refDb;
    private FirebaseUser user;
    private FirebaseAuth auth;

    public FirebaseData() {
        this.database = FirebaseDatabase.getInstance();
        this.refDb = database.getReference();
        this.auth = FirebaseAuth.getInstance();
        this.user = auth.getCurrentUser();


        //this.users = new ArrayList<>();
        refDb.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                users.add(user);
//                System.out.println("USEERRR");
//                System.out.println(user);
                collectUsers((Map<String, Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Failed");
            }
        });
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

//    public ArrayList<User> getRank() {
//        return this.users;
//    }


    public void collectUsers(Map<String, Object> users) {
        ArrayList<User> usersList = new ArrayList<User>();

        for (Map.Entry<String, Object> entry: users.entrySet()) {
            Map singleUser = (Map) entry.getValue();

            User user = new User((String) singleUser.get("username"), (Long) singleUser.get("score"));

            this.users.add(user);
         }
    }

    public Observable<List<User>> getUsers() {
        return Observable.just(users);
    }
}
