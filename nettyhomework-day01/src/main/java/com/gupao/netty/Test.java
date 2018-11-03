package com.gupao.netty;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        //运行服务器
        new Thread(new Runnable(){
            public void run() {
                BIOServer.start();
            }
        }).start();

        Thread.sleep(100);

        //运行客户端  发送第i个汉字
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while(true){
                    BIOClient.send(String.valueOf((char)(0x4e00+i)));
                    i++;
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
