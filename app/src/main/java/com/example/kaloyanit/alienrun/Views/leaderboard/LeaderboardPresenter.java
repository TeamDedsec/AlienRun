package com.example.kaloyanit.alienrun.Views.leaderboard;

import com.example.kaloyanit.alienrun.Data.FirebaseData;
import com.example.kaloyanit.alienrun.Models.User;
import com.example.kaloyanit.alienrun.Views.players.PlayersContracts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by KaloyanIT on 3/5/2017.
 */

public class LeaderboardPresenter implements LeaderboardContracts.Presenter {
    public LeaderboardContracts.View view;
    public FirebaseData data;
    private ArrayList<User> users;
    private DatabaseReference ref;

    public LeaderboardPresenter(LeaderboardContracts.View view, FirebaseData data) {
        this.view = view;
        this.view.setPresenter(this);



        this.data = data;
    }

    @Override
    public void start() {
//        this.data.addUser(10);
        //this.data.getData();
        //this.users = this.data.users;


        this.data.getUsers()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        view.setUsers(users);
                    }
                });

        //this.view.setUsers(this.users);
    }

    public void collectUsers(Map<String, Object> users) {
        HashSet<User> usersList = new HashSet<>();

        for (Map.Entry<String, Object> entry: users.entrySet()) {
            Map singleUser = (Map) entry.getValue();

            User user = new User((String) singleUser.get("username"), (Long) singleUser.get("score"));

            this.users.add(user);
        }
    }

    @Override
    public LeaderboardContracts.View getView() {
        return this.view;
    }
}
