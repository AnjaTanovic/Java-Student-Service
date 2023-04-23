package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anja Tanovic
 */
public class Server {

    private ServerSocket ssocket;
    private int port;
    private ArrayList<ServeConnectedClient> clients;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public ServerSocket getSsocket() {
        return ssocket;
    }

    public void setSsocket(ServerSocket ssocket) {
        this.ssocket = ssocket;
    }
    
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Server(int port) {
        this.clients = new ArrayList<>();
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        try {
            this.port = port;
            this.ssocket = new ServerSocket(port);
        } 
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void acceptClients() {
        Socket client = null;
        Thread thr;
        
        while(true) {
            try {
                System.out.println("Waiting for new clients...");
                client = this.ssocket.accept();
            } 
            catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (client != null) {
                System.out.println("Client is successfully connected.");
                //Create thread to serve client
                ServeConnectedClient servedClient = new ServeConnectedClient(client, clients, students, courses);
                clients.add(servedClient);
                thr = new Thread(servedClient);
                thr.start();
            }
            else {
                System.out.println("Client connection failed.");
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        Server srv = new Server(6001);
        
        System.out.println("Server is running and listening on port 6001.");
       
        srv.acceptClients();
    }
    
}
