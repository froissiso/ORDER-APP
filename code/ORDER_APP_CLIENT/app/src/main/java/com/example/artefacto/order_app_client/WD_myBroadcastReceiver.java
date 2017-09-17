package com.example.artefacto.order_app_client;

import android.content.BroadcastReceiver;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;



/*
Receive broadcasts for WIFI Direct tasks
 */
public class WD_myBroadcastReceiver extends BroadcastReceiver {
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private WD_ClientActivitySendOrder mActivity;
    private List<WifiP2pDevice> mPeers;
    private List<WifiP2pConfig> mConfigs;
    private WifiP2pDevice mDevice;

    public WD_myBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel, WD_ClientActivitySendOrder activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        System.out.println("P2P action = " + action);

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Determine if Wifi P2P mode is enabled or not, alert the Activity.
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);

            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {

                mActivity.setIsWifiEnabled(true);
                Log.e("Wifi-Direct Status", "Enabled");

            } else {

                mActivity.setIsWifiEnabled(false);
                Log.e("Wifi-Direct Status", "Disabled");

            }
        }
        else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // The peer list has changed!
            Log.e("Peer List", "Changed");
            mPeers = new ArrayList<WifiP2pDevice>();
            mConfigs = new ArrayList<WifiP2pConfig>();
            if (mManager!=null){

                WifiP2pManager.PeerListListener mpeerListListener = new PeerListListener() {
                    @Override
                    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {

                        mPeers.clear();
                        mPeers.addAll(wifiP2pDeviceList.getDeviceList());
                        mActivity.showPeers(mPeers);
                        for (int i=0;i<wifiP2pDeviceList.getDeviceList().size();i++){

                            WifiP2pConfig config = new WifiP2pConfig();
                            config.deviceAddress = mPeers.get(i).deviceAddress;
                            mConfigs.add(config);

                        }
                    }
                };
                mManager.requestPeers(mChannel,mpeerListListener);

            }
        }
        else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Connection state changed!  We should probably do something about that.
            if (mManager == null) {
                return;
            }

            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected()) {

                // We are connected with the other device, request connection
                // info to find group owner IP

                mManager.requestConnectionInfo(mChannel, infoListener);
            }
            else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

            }
        }
    }

    public void connect(int pos) {
        //Obtain name and address from the postion of the item selected in the Spinner
        WifiP2pConfig config = mConfigs.get(pos);
        config.groupOwnerIntent = 0; //Priority to become the HOST (0-15)
        mDevice = mPeers.get(pos);

        mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {

                Log.e("Connect Result", "Connected");

            }

            @Override
            public void onFailure(int i) {

                Log.e("Connect Result", "Connection Failed. Reason: "+i);

            }
        });
    }

    public void disconnect(){
        mManager.removeGroup(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {

                Log.e("Remove Group Result", "Removed");

            }

            @Override
            public void onFailure(int i) {

                Log.e("Remove Group Result", "Group Removal Failed: Error "+i);

            }
        });

    }

    WifiP2pManager.ConnectionInfoListener infoListener = new WifiP2pManager.ConnectionInfoListener() {

        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo info) {

            InetAddress groupOwnerAddress = info.groupOwnerAddress;

            if (info.groupFormed){
                mActivity.start(groupOwnerAddress);
            }
        }
    };
}