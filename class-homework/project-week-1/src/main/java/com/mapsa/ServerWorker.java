package com.mapsa;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerWorker extends Thread {
    private final Socket socketClient;
    private final Server server;
    private OutputStream outputStream;
    private String username = null;

    public ServerWorker(Socket socketClient, Server server) {
        this.socketClient = socketClient;
        this.server = server;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void run() {
        try {
            handleSocketClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSocketClient() throws IOException {
        InputStream inputStream = socketClient.getInputStream();
        this.outputStream = socketClient.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] commands = StringUtils.split(line);
            if ((commands != null) && commands.length > 0) {
                String cmd = commands[0];
                if (cmd.equalsIgnoreCase("login")) {
                    handleLogin(outputStream, commands);
                }
            }
        }
    }

    private void handleLogin(OutputStream outputStream, String[] commands) throws IOException {
        String username = commands[1];
        String password = commands[2];
        if ((username.equals("ali") && password.equals("123456")) ||
                (username.equals("esmaeil") && password.equals("123456"))) { // Todo input from file
            this.outputStream.write("Ok Login \n".getBytes());
            this.username = username;
            List<ServerWorker> workerList = server.getWorkerList();
            // other user's
            String onlineMsg = "online " + username + "\n";
            for (ServerWorker worker : workerList) { // Todo For i Or foreach
                if (worker.getUsername() != null) {
                    if (!username.equals(worker.getUsername())) {
                        worker.send(onlineMsg);
                    }
                }
            }
            // current
            for (ServerWorker worker : workerList) {
                if (worker.getUsername() != null) {
                    if (!username.equals(worker.getUsername())) {
                        send("online " + worker.getUsername() + "\n");
                    }
                }
            }

        } else {
            outputStream.write("Error Login".getBytes());
        }
    }

    private void send(String onlineMsg) throws IOException {
        outputStream.write(onlineMsg.getBytes());
    }
}
