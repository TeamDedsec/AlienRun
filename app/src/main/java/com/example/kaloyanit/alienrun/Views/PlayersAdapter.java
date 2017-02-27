package com.example.kaloyanit.alienrun.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;

import java.util.ArrayList;

/**
 * Created by KaloyanIT on 2/27/2017.
 */

public class PlayersAdapter extends ArrayAdapter<Player> {

    public PlayersAdapter(Context context) {
        this(context, new ArrayList<Player>());
    }

    public PlayersAdapter(Context context, ArrayList<Player> players) {
        super(context, -1, players);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_player, parent, false);
        }


        Player player = this.getItem(position);

        //Initialize item elements
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvSkill = (TextView) view.findViewById(R.id.tv_skills);
        TextView tvPrice = (TextView) view.findViewById(R.id.tv_price);
        ScalableView plImage = (ScalableView) view.findViewById(R.id.pl_image);
        Button buyButton = (Button) view.findViewById(R.id.buy_button);

        tvPrice.setText(String.format("%0$d", player.getPrice()));
        tvSkill.setText(player.getSkill());

        //plImage.setImageResource(player.getPictureId());
        //plImage.setBitmapImage(player.getImage());
        String title = player.getName();
        tvTitle.setText(title);

        return view;
    }
}
