package com.example.kaloyanit.alienrun.Views.players;


import com.example.kaloyanit.alienrun.Models.Player;

import java.util.List;

/**
 * Created by KaloyanIT on 2/22/2017.
 */

public class PlayersContracts {
    public interface View {
        void setPresenter(PlayersContracts.Presenter presenter);

        void setPlayers(List<Player> players);
    }

    public interface Presenter {
        PlayersContracts.View getView();

        void start();

        List<Player> getPlayers();
    }
}
