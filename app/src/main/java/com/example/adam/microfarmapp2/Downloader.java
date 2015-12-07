
//Author: John Schutz
//Group: MicroFarm

package com.example.adam.microfarmapp2;

//needs to run as AsyncTask method or Android won't allow network connection and crash
import android.os.AsyncTask;

import java.net.*;
import java.io.*;

public class Downloader extends AsyncTask {

    //the server listens on this port for Uploads from it (a download to here)
    private int serverPort = 8336;

    //filePath: path to the folder the CSV file is stored on
    private String filePath = "/data/data/com.example.adam.microfarmapp2/files";
    //fileName: name of the CSV file
    private String fileName = "log.csv";

    //IP address of the server
    private String ip = "24.220.58.163";

    //initialize a Socket variable
    private Socket socket;

    //buffer for reading from input stream
    private int BUFFER_SIZE = 64;

    public Downloader(){

    }

    @Override
    //return Object and params are needed as part of being an AsyncTask, though these features aren't used
    protected Object doInBackground(Object[] params) {
        //create the socket with this method
        createSocket();

        //create and InputStream using createSocketReader method
        InputStream input = createSocketReader();

        try {
            //get the length of the file to be downloaded so end is known when reached
            long numBytes = getFileSize(input);

            if (numBytes > 0) {
                //download file using downloadFileData method
                downloadFileData(input, numBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //cleanup
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //need a return, though none is used
        return null;
    }

    private void createSocket() {
        try {
            //attempt to establish connection with server
            socket = new Socket(ip, serverPort);
        } catch (UnknownHostException e) {
            System.err.println("Error connecting to peer '" + ip + "': unknown host");
        } catch (IOException e) {
            System.err.println("Error connecting to peer '" + ip + "': could not create socket");
        }
    }

    private InputStream createSocketReader() {
        InputStream in = null;
        try {
            //attempt to create an input stream with the socket
            in = socket.getInputStream();
        } catch (IOException e) {
            System.err.println("Error connecting to peer '" + ip + "': could not get input stream");
        }
        return in;
    }

    private long getFileSize(InputStream socketIn) throws IOException {
        //create a DataInputStream using the current InputStream
        DataInputStream dataIn = new DataInputStream(socketIn);
        //read the next long, store it, and return it as the file size
        long fileSize = dataIn.readLong();
        return fileSize;
    }

    private void downloadFileData(InputStream socketIn, long fileSize) throws IOException {
        //create File objects eventually leading to the CSV file
        File dir = new File(filePath);
        File downloadFile = new File(dir, fileName);

        //create a FileOutputStream to the download location
        FileOutputStream fileOut = new FileOutputStream(downloadFile);

        //keep track of read bytes
        long totalReadBytes = 0L;

        //create the byte array that will be used as the buffer
        byte[] buffer = new byte[BUFFER_SIZE];

        //while bytes read is less than file size, more bytes to read
        while (totalReadBytes < fileSize) {
            //file the buffer and count bytes added to buffer
            int readBytes = socketIn.read(buffer);
            //write buffer to output stream
            fileOut.write(buffer, 0, readBytes);
            //add read bytes to total bytes
            totalReadBytes += readBytes;
        }
        //send output stream to file
        fileOut.flush();
    }
}
