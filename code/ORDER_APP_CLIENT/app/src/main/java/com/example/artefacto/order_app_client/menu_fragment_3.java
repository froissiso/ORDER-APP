package com.example.artefacto.order_app_client;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ExpandableListView;
import android.widget.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fjrois on 16/11/16.
 */
public class menu_fragment_3 extends Fragment {

    public static menu_fragment_3 newInstance(){
        menu_fragment_3 frag = new menu_fragment_3();
        return frag;
    }

    public menu_fragment_3(){}

    private Button button1;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mapCollection;
    ExpandableListView expandableListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.menu_fragment_3, container, false);


        return rootView;
    }

}

