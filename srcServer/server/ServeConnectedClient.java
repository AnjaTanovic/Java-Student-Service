package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anja Tanovic
 */
public class ServeConnectedClient implements Runnable {
    
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private ArrayList<ServeConnectedClient> allClients;
    
    private String userName;
    private String password;
    private String role;
    
    private final String users_txt = "D:\\Anja\\FAKULTET\\Master\\Razvoj softvera za embeded operativne sisteme\\NetBeans\\Zadaci\\2\\Java-Student-Service\\srcServer\\server\\users.txt";
    File users_file;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ServeConnectedClient(Socket socket, ArrayList<ServeConnectedClient> allClients) {
        this.socket = socket;
        this.allClients = allClients;
        
        //get InputStream and OutputStream from socket
        try {
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
            this.pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
            
            //user data is still not known
            this.userName = "";
            this.password = "";
            this.role = "";
        } catch (IOException ex) {
            Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //read file with users
        users_file = new File(users_txt);
        if (!users_file.exists() || !users_file.isFile()) {
                System.out.println("File is not found.");
                System.exit(1);
        }
    }
    
    @Override
    public void run() {
        System.out.println("Run");
        
        /*
        while(true) {
            
        }
        */
    }
    
    void updateClientStatus() {
     
    }
}
