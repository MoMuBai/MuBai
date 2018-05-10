package com.mb.mubai.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: lzw
 * Date: 2018/4/20
 * Description: This is Server
 */

public class Server {
    private static int PORT = 8379;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            System.out.println("服务端启动...");
            Socket socket = serverSocket.accept();
            new Thread(new ServerHandler(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
