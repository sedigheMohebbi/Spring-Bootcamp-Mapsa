package com.mapsa;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class ChatApplication {
    public static void main(String[] args) {
        int port = 8090; // Todo input port in file (properties) (Done)
        File file=new File(args[0]);
        try(InputStream inputStream=new FileInputStream(file)) {
            Properties p=new Properties();
            p.load(inputStream);
            port= Integer.parseInt(p.getProperty("port"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Server server = new Server(port);
        server.start();
    }


}
