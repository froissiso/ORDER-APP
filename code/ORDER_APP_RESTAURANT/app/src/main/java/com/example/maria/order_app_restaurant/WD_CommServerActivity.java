package com.example.maria.order_app_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class WD_CommServerActivity extends AppCompatActivity {

    int port=8888;

    ServerThread serverThread;

    InetAddress hostAddress;
    String stringHostAddress;

    TextView msgReceived;
    Button orderReady;
    String readySoon = "Your order will be ready soon";
    String ready = "Enjoy!";

    static boolean orderready = false;

    Handler mReceivedHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_activity_comm_server);
        mReceivedHandler = new Handler(){
            public void handleMessage(Message msg){
                //msgReceived.setText(msg.getData().getString("Received Message"));
                Intent i = new Intent(getBaseContext(), My2ndActivity.class);
                i.putExtra("message", msg.getData().getString("Received Message"));
                startActivity(i);


            }


        };

                Intent intent = getIntent();

                if (intent.getBooleanExtra("Connected", false)){


                    //Get Host address
                    stringHostAddress = intent.getStringExtra("HostAddress");

                    //Convert it to an InetAddress
                    try {
                        hostAddress = InetAddress.getByName(stringHostAddress);

                    }catch (UnknownHostException Exc){

                    }
                        serverThread = new ServerThread(port);
                        serverThread.start();

                }


    }

    public class ServerThread extends Thread {


        int mPort = 0;
        InetAddress mClientAddress;

        DatagramSocket socket;

        byte[] sendData = new byte[64];
        byte[] receiveData = new byte[64];
        String receivedString;

        public ServerThread(int port) {

            this.mPort = port;

        }

        @Override
        public void run() {

            try {
                //Try to open the socket if it has not already been done
                if (socket == null) {

                    socket = new DatagramSocket(null);
                    socket.setReuseAddress(true);
                    socket.bind(new InetSocketAddress(mPort));
                    socket.setSoTimeout(1);
                }

            } catch (IOException exc) {
                if (exc.getMessage() == null) {
                    Log.e("Set Socket", "Unknown Message");
                } else {
                    Log.e("Set Socket", exc.getMessage());
                }
            }


            //Receive
            //Note: Server can not send until it receives and get the address

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);


            while(receivedString == null) {
                //Try for receiving
                try {
                    Log.e("MyTag", "Waiting for packet");
                    socket.receive(receivePacket);
                    receivePacket.getData();
                    receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());

                    updateReceived(receivedString);
                    Log.e("Message received", receivedString);

                    //Get Client's address if not know yet. Only will enter for the first packet
                    if (mClientAddress == null) {

                        mClientAddress = receivePacket.getAddress();
                    }
                } catch (IOException exc) {
                    if (exc.getMessage() == null) {
                        Log.e("Receive", "Null Exception: Likely Timeout");
                    } else {
                        Log.e("Receive", exc.getMessage());
                    }
                }
            }


            //Try for sending
            try {

                if (mClientAddress != null) {

                    sendData = readySoon.getBytes();

                    DatagramPacket packet = new DatagramPacket(sendData, sendData.length, mClientAddress, mPort);

                    socket.send(packet);

                    Log.e("Message sent", readySoon);

                }

            } catch (IOException exc) {
                if (exc.getMessage() == null) {
                    Log.e("Sender", "Null Exception: Likely Timeout");
                } else {
                    Log.e("Sender", exc.getMessage());
                }
            }

            try {
                //if (mClientAddress != null) {
                while(orderready == false){

                }
                //orderready = true;

                sendData = ready.getBytes();

                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, mClientAddress, mPort);

                socket.send(packet);

                Log.e("Message sent", ready);



                //}

            }catch (IOException exc) {
                if (exc.getMessage() == null) {
                    Log.e("Sender", "Null Exception: Likely Timeout");
                } else {
                    Log.e("Sender", exc.getMessage());
                }
            }


        }
    }

        public void updateReceived(String message){

            Bundle msgBundle = new Bundle();
            msgBundle.putString("Received Message", message);
            Message msg = new Message();
            msg.setData(msgBundle);
            mReceivedHandler.sendMessage(msg);

        }
}
