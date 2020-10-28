package com.mapsa.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author esmaeil
 * @date 28.10.20
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9090);
            while (true) {
                Socket socket = server.accept();
                OutputStream output = socket.getOutputStream();
                output.write(("Please input your name: ").getBytes());
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = reader.readLine();
                output.write(("Hello " + line + "\n").getBytes());
                output.close();
                input.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
