package com.mapsa;

public class ChatApplication {
    public static void main(String[] args) {
        int port = 8090; // Todo input port in file (properties)
        Server server = new Server(port);
        server.start();
    }
}
