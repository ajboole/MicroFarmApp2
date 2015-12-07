package com.example.adam.microfarmapp2;

import android.os.AsyncTask;

import java.net.*;
import java.io.*;

public class Uploader extends AsyncTask {

    private int serverPort = 8335;

    private String filePath = "/data/data/com.example.adam.microfarmapp2/files";
    private String fileName = "log.csv";

    private String ip = "24.220.58.163";

    private Socket socket;

    public Uploader(){
    }

    @Override
    protected Object doInBackground(Object[] params) {
        createSocket();

        try{
            DataOutputStream dOutStream = new DataOutputStream(socket.getOutputStream());

            File dirLocation = new File(filePath,fileName);

            FileInputStream fileInStream = new FileInputStream(dirLocation);

            dOutStream.writeLong(dirLocation.length());
            dOutStream.flush();

            byte[] byteArray = new byte[64];

            int endOfFile = fileInStream.read(byteArray);

            while(endOfFile != -1) {
                dOutStream.write(byteArray, 0, endOfFile);
                dOutStream.flush();
                endOfFile = fileInStream.read(byteArray);
            }

            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    private void createSocket() {
        try {
            socket = new Socket(ip, serverPort);
        } catch (UnknownHostException e) {
            System.err.println("Error connecting to peer '" + ip + "': unknown host");
        } catch (IOException e) {
            System.err.println("Error connecting to peer '" + ip + "': could not create socket");
        }
    }

}