package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    
    ObjectInputStream inCourses;
    ObjectInputStream inStudents;
    
    private final String coursesTxt = "D:\\Anja\\FAKULTET\\Master\\Razvoj softvera za embeded operativne sisteme\\NetBeans\\Zadaci\\2\\Java-Student-Service\\srcServer\\server\\courses.txt";
    private final String studentsTxt = "D:\\Anja\\FAKULTET\\Master\\Razvoj softvera za embeded operativne sisteme\\NetBeans\\Zadaci\\2\\Java-Student-Service\\srcServer\\server\\students.txt";
    
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
        
        //load students
        try {
            inStudents = new ObjectInputStream(new FileInputStream(studentsTxt));
            ArrayList<Student> sts = new ArrayList<>();
            sts =(ArrayList<Student>) inStudents.readObject();
            this.students = sts;
            inStudents.close();
        }catch (IOException ex) {
            Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //load courses
        try {
            inCourses = new ObjectInputStream(new FileInputStream(coursesTxt));
            ArrayList<Course> crs = new ArrayList<>();
            crs =(ArrayList<Course>) inCourses.readObject();
            this.courses = crs;
            inCourses.close();
        }catch (IOException ex) {
            Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void acceptClients() {
        Socket client = null;
        Thread thrClients;
        
        //Create thread to wait for exit
        Thread thrTerm;
        ServerTerminate termServer = new ServerTerminate(students, courses);
        thrTerm = new Thread(termServer);
        thrTerm.start();
        
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
                thrClients = new Thread(servedClient);
                thrClients.start();
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
        System.out.println("Enter \"EXIT\" to terminate server.");
       
        srv.acceptClients();
    }
    
}
