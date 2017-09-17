package com.example.artefacto.order_app_client;

/**
 * Created by fjrois on 27/11/16.
 */
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class WD_CommClientActivity extends AppCompatActivity {

    int port=8888;

    ClientThread clientThread;

    InetAddress hostAddress;
    String stringHostAddress;

    TextView msgReceived;
    TextView msgReceived2;
    String message;

    Handler mReceivedHandler;
    Handler mReceivedHandler2;

    String receivedMessage;

    ImageView imageView_cooking_ready;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_activity_comm_client);

        msgReceived = (TextView) findViewById(R.id.textView);
        msgReceived2 = (TextView) findViewById(R.id.textView2);

        imageView_cooking_ready = (ImageView) findViewById(R.id.image_cooking_ready);

        mReceivedHandler = new Handler(){
            public void handleMessage(Message msg){
                msgReceived.setText(msg.getData().getString("Received Message"));
            }
        };

        msgReceived.setText(receivedMessage);

        mReceivedHandler2 = new Handler(){
            public void handleMessage(Message msg){
                msgReceived.setText("");
                msgReceived2.setText(msg.getData().getString("Received Message"));
                imageView_cooking_ready.setImageResource(R.drawable.food_ready);
            }
        };

        msgReceived2.setText(receivedMessage);

        Intent intent = getIntent();

        message = intent.getStringExtra("Message");

        if (intent.getBooleanExtra("Connected", false)){

            //Get Host address
            stringHostAddress = intent.getStringExtra("HostAddress");

            //Convert it to an InetAddress
            try {
                hostAddress = InetAddress.getByName(stringHostAddress);

            }catch (UnknownHostException Exc){

            }

            clientThread = new ClientThread(hostAddress,port,message);
            clientThread.start();


        }
    }


    public class ClientThread extends Thread {

        int mPort=0;
        InetAddress mHostAddress;

        DatagramSocket socket;

        byte[] sendData = new byte[64];
        byte[] receiveData = new byte[64];
        String receivedString;
        String receivedString2;
        String mMessage;

        public ClientThread (InetAddress hostAddress, int port, String message){

            this.mHostAddress = hostAddress;
            this.mPort = port;
            this.mMessage = message;
        }

        @Override
        public void run() {
            //Confirm host address and port number established
            if (mHostAddress!=null && mPort!= 0){

                try{

                    if (socket == null){
                        socket = new DatagramSocket(null);
                        socket.setReuseAddress(true);
                        socket.bind(new InetSocketAddress(mPort));
                        socket.setSoTimeout(1);
                    }

                }catch (IOException exc){

                    if(exc.getMessage()==null){
                        Log.e("Set Socket", "Unknown message");
                    }else {
                        Log.e("Set Socket", exc.getMessage());
                    }

                }

                //Try for sending
                try {
                    //Convert the message into bytes
                    sendData = mMessage.getBytes();

                    //UDP packet
                    DatagramPacket packet = new DatagramPacket(sendData, sendData.length, mHostAddress,mPort);

                    socket.send(packet);
                    Log.e("MyTag", "Client: Packet Sent");

                } catch (IOException exc) {
                    if (exc.getMessage() == null){
                        Log.e("Set Socket", "Unknown message: Likely Timeout");
                    }else{
                        Log.e("Set Socket", exc.getMessage());
                    }
                }

                while(receivedString == null || receivedString2 == null) {//Try for receiving

                    if(receivedString == null){
                        try {

                            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                            socket.receive(receivePacket);

                            receivePacket.getData();

                            receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                            Log.d("Received String",receivedString);
                            updateReceived(receivedString);

                        } catch (IOException exc) {
                            if (exc.getMessage() == null) {
                                Log.e("Set Socket", "Unknown message");
                            } else {
                                Log.e("Set Socket", exc.getMessage());
                            }
                        }
                    }
                    else{
                        try {

                            DatagramPacket receivePacket2 = new DatagramPacket(receiveData, receiveData.length);

                            socket.receive(receivePacket2);

                            receivePacket2.getData();

                            receivedString2 = new String(receivePacket2.getData(), 0, receivePacket2.getLength());
                            Log.d("Received String",receivedString2);
                            updateReceived2(receivedString2);

                        } catch (IOException exc) {
                            if (exc.getMessage() == null) {
                                Log.e("Set Socket", "Unknown message 2");
                            } else {
                                Log.e("Set Socket", exc.getMessage());
                            }
                        }

                    }
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
    public void updateReceived2(String message){

        Bundle msgBundle = new Bundle();
        msgBundle.putString("Received Message", message);


        Message msg = new Message();
        msg.setData(msgBundle);
        mReceivedHandler2.sendMessage(msg);

    }
}



