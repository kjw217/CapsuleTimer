package com.example.knight.a2018_mobile;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Knight on 2018. 4. 30..
 *
 * Socket class to communicate with web server
 *
 */

public class MySocket extends Thread {
    Socket sock;
    BufferedReader networkReader;
    BufferedWriter networkWriter;
    PrintWriter out;
    String Server_IP;
    String request;
    String res = "";
    int Server_PORT;
    int successive = 0;
    int isResponse = 1;

    /**
     * @description Constructor of socket class
     * @param new_Server_IP Server IP address
     * @param new_Server_PORT Server port number
     */
    public MySocket(String new_Server_IP, int new_Server_PORT){
        Server_IP = new_Server_IP;
        Server_PORT = new_Server_PORT;
//        sharedPreferences = getSharedPreferences("test", MODE_
    }

    /**
     * @description Send request to server and return result of request
     * @param request Request to web server
     * @return Result of request to web server
     */
    public String request(String request){
        this.request = request;
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (res == null) Log.d("result", "null");
        else Log.d("result", res);

        return res;
    }

    public void request(String request, int flag) {
        this.request = request;
        this.isResponse = flag;
        this.start();
        Log.d("SOCKET", "Image Upload");
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  @description Create socket, send request to server through socket and receive result with socket
     */
    public void run() {
        try {
            if (isResponse == 1) {
                sock = new Socket(Server_IP, Server_PORT);
                networkReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                networkWriter = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
            }
            Log.d("Socket", request);
            networkWriter.write(request);
            networkWriter.flush();
            if (isResponse == 1) {
                res = networkReader.readLine();
            }
            successive = 1;
        } catch (IOException e) {
            Log.d("Socket", "Socket initialization is failed, " + e.getMessage());
            successive = 0;
        }
    }
}
