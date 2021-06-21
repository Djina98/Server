/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import rs.ac.bg.fon.ps.session.ClientSession;

/**
 *
 * @author Djina
 */
public class Server extends Thread {
    
    private int port;
    ServerSocket serverSocket;
    ArrayList<ProcessClientsRequests> users;
    
    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        this.users = new ArrayList<>();
    }
    
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("New client has just connected!");
                ProcessClientsRequests processClientsRequests = new ProcessClientsRequests(socket);
                users.add(processClientsRequests);
                processClientsRequests.start();

            }
        } catch (Exception ex) {
            System.out.println("Server has been stopped!");
        }
    }

    public void stopServer() throws IOException {
        serverSocket.close();
        for (ProcessClientsRequests user : users) {
            user.getSocket().close();
            
        }
    }
    
}
