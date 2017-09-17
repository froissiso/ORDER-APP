package com.example.artefacto.order_app_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fjrois on 15/11/16.
 */

public class PlacesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Place> itemsArrayList;
    private PlacesDB db;

    public PlacesAdapter(Context context, ArrayList<Place> itemsArrayList) {
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public int getCount() {
        return itemsArrayList.size();
    }

    @Override
    public Place getItem(int position) {
        return itemsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }
        db = new PlacesDB(context);

        final Place item = itemsArrayList.get(position);

        ImageView imagePlace1 = (ImageView) view.findViewById(R.id.image_place);
        TextView namePlace1 = (TextView) view.findViewById(R.id.text_place_name);
        namePlace1.setText(item.getName());
        //Use Glide for improved image loading. App should function more fluently

        /*
        Glide.with(context)

                .load(context.getResources().getDrawable(Integer.parseInt(item.getIdDrawable())))
                .into(imagePlace1);

        */

        //Use Glide for improved image loading. App should function more fluently
        imagePlace1.setImageResource(Integer.parseInt(item.getIdDrawable()));
        return view;
    }
}


