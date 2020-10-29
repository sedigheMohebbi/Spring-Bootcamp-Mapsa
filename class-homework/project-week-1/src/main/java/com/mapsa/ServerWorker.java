package com.mapsa;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerWorker extends Thread {
    private final Socket socketClient;
    private final Server server;
    private OutputStream outputStream;
    private String username = null;
    private Set<String> topics = new HashSet<String>();

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
                } else if ("logoff".equalsIgnoreCase(cmd)) {
                    handleLogoff();
                    break;
                } else if ("msg".equalsIgnoreCase(cmd)) {
                    handleMsg(line);
                } else if ("join".equalsIgnoreCase(cmd)) {
                    handleTopic(commands);
                } else if ("exit".equalsIgnoreCase(cmd)) {
                    handleExit(commands); // Todo msg to topic
                }
            }
        }
    }

    private void handleExit(String[] commands) {
        if (commands.length > 1) {
            topics.remove(commands[1]);
        }
    }

    private void handleTopic(String[] commands) {
        if (commands.length > 1) {
            topics.add(commands[1]);
        }
    }

    private void handleMsg(String line) throws IOException {
        String[] msgLine = StringUtils.split(line, null, 3);
        System.out.println(msgLine[2]); // test

        String sender = msgLine[1];
        String body = msgLine[2];

        boolean isTopic = sender.charAt(0) == '#';
        List<ServerWorker> workerList = server.getWorkerList();
        for (ServerWorker worker : workerList) {
            if (isTopic) {
                if (worker.isMemberOfTopic(sender)) {
                    String msg = "from " + this.username + " To Topic:" + sender + " " + body + "\n";
                    worker.send(msg);
                }
            } else {
                if (sender.equals(worker.getUsername())) {
                    String msg = "from " + this.username + ": " + body + "\n";
                    worker.send(msg);
                }
            }

        }

    }

    private boolean isMemberOfTopic(String sender) {
        return topics.contains(sender);
    }


    private void handleLogoff() throws IOException {
        server.removeUser(this);
        String msgOffline = "offline " + username + "\n";
        List<ServerWorker> workerList = server.getWorkerList();
        for (ServerWorker worker : workerList) {
            worker.send(msgOffline);
        }
        socketClient.close();
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
