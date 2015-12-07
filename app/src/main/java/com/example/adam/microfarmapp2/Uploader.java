
//Author: John Schutz
//Group: MicroFarm

package com.example.adam.microfarmapp2;

//needs to run as AsyncTask method or Android won't allow network connection and crash
import android.os.AsyncTask;

import java.net.*;
import java.io.*;

public class Uploader extends AsyncTask {

    //server listens on port 'BEES' for uploads to it
    private int serverPort = 8335;

    //filePath: path to folder containing CSV file
    private String filePath = "/data/data/com.example.adam.microfarmapp2/files";
    //fileName: name of CSV file
    private String fileName = "log.csv";

    //IP address of the server
    private String ip = "24.220.58.163";

    //initialize a socket
    private Socket socket;

    public Uploader(){
    }

    @Override
    //return Object and params are needed as part of being an AsyncTask, though these features aren't used
    protected Object doInBackground(Object[] params) {

        //create a socket connection with this method
        createSocket();

        try{
            //create a DataOutputStream to the socket
            DataOutputStream dOutStream = new DataOutputStream(socket.getOutputStream());

            //create a File object leading to and including the CSV file on the Android
            File dirLocation = new File(filePath,fileName);

            //create a FileInputStream to the Android CSV file
            FileInputStream fileInStream = new FileInputStream(dirLocation);

            //write the length of the CSV file to the output stream
            dOutStream.writeLong(dirLocation.length());
            dOutStream.flush();//send the output stream to the server

            //create byte array of length 64
            byte[] byteArray = new byte[64];

            //endOfFile at -1, fileInStream.read(byteArray) fills byte array with data
            int endOfFile = fileInStream.read(byteArray);

            while(endOfFile != -1) {
                //write to output stream the data in the byte array
                dOutStream.write(byteArray, 0, endOfFile);
                //send output stream to the server
                dOutStream.flush();
                //repeat filling the byte array
                endOfFile = fileInStream.read(byteArray);
            }

            //cleanup
            dOutStream.close();
            fileInStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        //returned value isn't used, so return nothing
        return null;
    }


    private void createSocket() {
        try {
            //attempt to make connection to server
            socket = new Socket(ip, serverPort);
        } catch (UnknownHostException e) {
            System.err.println("Error connecting to peer '" + ip + "': unknown host");
        } catch (IOException e) {
            System.err.println("Error connecting to peer '" + ip + "': could not create socket");
        }
    }

}