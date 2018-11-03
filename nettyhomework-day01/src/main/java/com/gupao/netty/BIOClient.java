package com.gupao.netty;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BIOClient {

    private static int CLIENT_PORT = 5125;

    private static String CLIENT_ADDRESS = "127.0.0.1";

    private static int i = 1;

    public static void send(String character){
        send(CLIENT_PORT,character);
    }

    private static void send(int port, String character) {
        System.out.println("character:" + character);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter printWriter = null;

        try {
            socket = new Socket(CLIENT_ADDRESS,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println(character);
            System.out.println("第"+i+"个汉字为："+ character);
            i++;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket = null;

            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            in = null;

            if(printWriter != null){
                printWriter.close();
                printWriter = null;
            }
        }
    }
}
