package com.gupao.netty;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadHandler implements Runnable {

    private Socket socket;

    private static int i = 1;

    public ThreadHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        BufferedReader in = null;
        PrintWriter printWriter = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
            String character;
            while(true){
                if((character = in.readLine()) == null){
                    break;
                }
                System.out.println("服务器收到第"+i+"个文字为:" + character);
            }
            i++;
            printWriter.println(character);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
            }
            printWriter = null;

            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket = null;

        }

    }
}
