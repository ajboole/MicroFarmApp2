package com.example.adam.microfarmapp2;

import android.os.AsyncTask;

import java.net.*;
import java.io.*;

public class Downloader extends AsyncTask {

    private int serverPort = 8336;

    private String filePath = "/data/data/com.example.adam.microfarmapp2/files";
    private String fileName = "log.csv";

    private String ip = "24.220.58.163";

    private Socket socket;

    private int BUFFER_SIZE = 64;

    public Downloader(){

    }

    @Override
    protected Object doInBackground(Object[] params) {
        createSocket();
        InputStream input = createSocketReader();

        try {
            long numBytes = getFileSize(input);
            if (numBytes > 0) {
                downloadFileData(input, numBytes);
            }
        } catch (IOException e) {
            System.err.println("Error while downloading");
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            // but otherwise, this should be okay
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

    private InputStream createSocketReader() {
        InputStream in = null;
        try {
            in = socket.getInputStream();
        } catch (IOException e) {
            System.err.println("Error connecting to peer '" + ip + "': could not get input stream");
        }
        return in;
    }

    private long getFileSize(InputStream socketIn) throws IOException {
        DataInputStream dataIn = new DataInputStream(socketIn);
        long fileSize = dataIn.readLong();
        return fileSize;
    }

    private void downloadFileData(InputStream socketIn, long fileSize) throws IOException {
        File dir = new File(filePath);
        File downloadFile = new File(dir, fileName);
        FileOutputStream fileOut = new FileOutputStream(downloadFile);
        long totalReadBytes = 0L;
        byte[] buffer = new byte[BUFFER_SIZE];
        while (totalReadBytes < fileSize) {
            int readBytes = socketIn.read(buffer);
            fileOut.write(buffer, 0, readBytes);
            totalReadBytes += readBytes;
        }
        fileOut.flush();
    }
}
