package com.example.artefacto.order_app_client;

/**
 * Created by fjrois on 27/11/16.
 */
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.ArrayList;


public class WD_peerListAdaptor extends ArrayAdapter<String> {

    private final Activity Context;
    private ArrayList<String> PeerNames;

    public WD_peerListAdaptor(Activity context, ArrayList<String> peerNames) {

        super(context, R.layout.wd_mypeers, peerNames);
        this.Context = context;
        this.PeerNames = peerNames;

    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = Context.getLayoutInflater();
        View ListViewSingle = inflater.inflate(R.layout.wd_mypeers, null, true);
        TextView ListViewItems = (TextView) ListViewSingle.findViewById(R.id.peerName);

        ListViewItems.setText(PeerNames.get(position));
        return ListViewSingle;

    }

}