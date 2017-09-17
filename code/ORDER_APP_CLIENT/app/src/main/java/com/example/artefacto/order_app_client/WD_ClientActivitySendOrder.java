package com.example.artefacto.order_app_client;

/**
 * Created by fjrois on 27/11/16.
 */
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.InetAddress;
import java.util.List;
import java.util.ArrayList;


public class WD_ClientActivitySendOrder extends AppCompatActivity {

    private final IntentFilter mintentFilter = new IntentFilter();
    private WifiP2pManager.Channel mChannel; //this channel is used to connect the app to the wifi framework
    private WifiP2pManager mManager;//wifi manager
    private WD_myBroadcastReceiver mBroadcastReceiver;//will catch event and do some stuff
    boolean isWifiEnabled;
    ListView peerList;
    ArrayList<String> peerNames = new ArrayList<>();
    ListAdapter peerAdapter;
    int position;
    //String message = "Drinks and Food";

    String order_chain;
    TextView order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_client_activity_send_order);


        order_chain = getIntent().getStringExtra("order_chain");


        //Intent intent = getIntent();

        //message = intent.getStringExtra("Message");

        //  Indicates a change in the Wi-Fi P2P status.
        mintentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        // Indicates a change in the list of available peers.
        mintentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        // Indicates the state of Wi-Fi P2P connectivity has changed.
        mintentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        // Indicates this device's details have changed.
        mintentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mBroadcastReceiver = new WD_myBroadcastReceiver(mManager,mChannel,this);

        peerList = (ListView) findViewById(R.id.peerList);
        peerAdapter = new WD_peerListAdaptor(WD_ClientActivitySendOrder.this, peerNames);
        peerList.setAdapter(peerAdapter);
        peerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                connectPeers(position);
            }
        });

        Button discover = (Button) findViewById(R.id.discover);
        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Action", "Discover Peers");
                discoverPeers();
            }
        });
    }

    public void discoverPeers(){
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {

                Log.e("Discovery Result","SUCCESFUL");
            }

            @Override
            public void onFailure(int reasonCode) {

                Log.e("Discovery Result","UNSUCCESFUL");
            }
        });
    }

    public void connectPeers(int pos){
        mBroadcastReceiver.connect(pos);
    }

    public void showPeers(List<WifiP2pDevice> peerList){

        this.peerNames.clear();
        for (int i=0; i<peerList.size(); i++){
            this.peerNames.add(peerList.get(i).deviceName);
        }
        Log.e("","Update Peer List");
        ((BaseAdapter)this.peerList.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onResume(){
        super.onResume();
        mBroadcastReceiver = new WD_myBroadcastReceiver(mManager,mChannel,this);
        registerReceiver(mBroadcastReceiver, mintentFilter);

        //Cuando volvemos al menú inicial, hay que desconectar, sino salta automaticamente a la segunda activity
        mBroadcastReceiver.disconnect();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }


    public void setIsWifiEnabled(boolean isEnabled) {
        isWifiEnabled = isEnabled;
    }

    public void start(InetAddress groupOwnerAddress) {

        Intent p2pintent = new Intent(this,WD_CommClientActivity.class);
        p2pintent.putExtra("HostAddress", groupOwnerAddress.getHostAddress());
        p2pintent.putExtra("Connected", true);
        p2pintent.putExtra("Message", order_chain);
        Log.e("","Start");
        startActivity(p2pintent);

    }
}

