package com.example.artefacto.order_app_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by fjrois on 16/11/16.
 */
public class MainAdapter extends BaseAdapter {
    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Title.ITEMS.length;
    }

    @Override
    public Title getItem(int position) {
        return Title.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_main, viewGroup, false);
        }

        ImageView image0 = (ImageView) view.findViewById(R.id.image_place);
        TextView name0 = (TextView) view.findViewById(R.id.text_place_name);

        final Title item = getItem(position);
        /*imagePlace.setImageResource(item.getIdDrawable());
        namePlace.setText(item.getName());
        */

        //Use Glide for improved image loading. App should function more fluently
        Glide.with(image0.getContext())
                .load(item.getIdDrawable())
                .into(image0);

        name0.setText(item.getName());

        return view;
    }

}