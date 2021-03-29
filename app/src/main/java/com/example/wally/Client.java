package com.example.wally;
import java.net.*;
import java.io.*;

public class Client extends Thread{

    private String _data = "";
    private String _address;
    private int _port;

    public Client(String command, String address, int port){
        this._data = command;
        this._address = address;
        this._port = port;
    }

    public void run(){
        try {
            this.main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void main() throws IOException {
        Socket client_socket = new Socket(this._address, this._port);
        client_socket.getOutputStream().write(this._data.getBytes());
        client_socket.close();
    }

}
