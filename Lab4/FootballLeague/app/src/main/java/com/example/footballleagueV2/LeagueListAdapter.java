package com.example.footballleagueV2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LeagueListAdapter extends ArrayAdapter<League> {

    private static final String TAG = "LeagueListAdapter";

    private Context mContext;
    int mResource;


    public LeagueListAdapter(Context context, int resource, ArrayList<League> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getLeagueName();
        int nameID = getItem(position).getLeagueID();

        League nLeague = new League(name, nameID);

        nLeague.setLeagueName(name);
        nLeague.setLeagueID(nameID);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvLeagueName);

        tvName.setText(name);

        Log.d("getView", name);

        return convertView;
    }
}
