package com.example.kaloyanit.alienrun.Views.players;

import com.example.kaloyanit.alienrun.Data.LocalData;
import com.example.kaloyanit.alienrun.Data.base.BaseData;
import com.example.kaloyanit.alienrun.Models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by KaloyanIT on 2/22/2017.
 */

public class PlayersPresenter implements PlayersContracts.Presenter {
    private final PlayersContracts.View view;
    //private List<Player> players;

    private final LocalData<Player> data;

    public PlayersPresenter(PlayersContracts.View view, LocalData<Player> playerLocalData) {
        this.view = view;
        this.getView().setPresenter(this);

        this.data = playerLocalData;
    }
    @Override
    public PlayersContracts.View getView() {
        return this.view;
    }

    @Override
    public void start() {
        this.data.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<Player[], List<Player>>() {
//                    @Override
//                    public List<Player> apply(Player[] players) throws Exception {
//                        ArrayList<Player> playersResult = new ArrayList<Player>();
//                        for (int i = 0; i < players.length ; i++) {
//                            playersResult.add(players[i]);
//                        }
//                        return playersResult;
//                    }
//                })
//                .subscribe(new Consumer<List<Player>>() {
//                    @Override
//                    public void accept(List<Player> players) throws Exception {
//                        ArrayList<Player> accepted = new ArrayList<Player>(players);
//                        getView().setPlayers(accepted);
//                    }
//                });
                .subscribe(new Consumer<List<Player>>() {
                    @Override
                    public void accept(List<Player> players) throws Exception {
                        getView().setPlayers(players);
                    }
                });
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<Player>();
    }


}
