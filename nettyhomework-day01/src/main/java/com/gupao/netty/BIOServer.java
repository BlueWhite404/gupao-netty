package com.gupao.netty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

    private static int SERVER_PORT = 5125;

    private static ServerSocket serverSocket;

    public static void start(){
        startServer(SERVER_PORT);
    }

    private static void startServer(int port) {
        try{
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ThreadHandler(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket = null;
        }

    }
}
