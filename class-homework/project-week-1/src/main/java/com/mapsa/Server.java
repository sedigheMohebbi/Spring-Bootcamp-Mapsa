package com.mapsa;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private final int port;
    private List<ServerWorker> workerList = new ArrayList<ServerWorker>();

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("start server... ");
            while (true) {
                Socket socketClient = serverSocket.accept();
                System.out.println("client address: " + socketClient);
                ServerWorker worker = new ServerWorker(socketClient, this);
                workerList.add(worker);
                worker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ServerWorker> getWorkerList() {
        return workerList;
    }

    public void removeUser(ServerWorker worker) {
        workerList.remove(worker);
    }
}
