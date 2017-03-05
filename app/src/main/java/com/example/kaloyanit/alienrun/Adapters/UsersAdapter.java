package com.example.kaloyanit.alienrun.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.Models.User;
import com.example.kaloyanit.alienrun.R;

import java.util.ArrayList;

/**
 * Created by KaloyanIT on 3/5/2017.
 */

public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(Context context) {
        this(context, new ArrayList<User>());
    }

    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, -1, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_user, parent, false);
        }




        return view;
    }
}
