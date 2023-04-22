package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    
    private int clientState;
    //0 - signed in state, 1 - loged in state
    
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
            this.clientState = 0;
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
        
        while(true) {
            switch (clientState) {
                case 0:
                    try {
                        //check username and password
                        String userInfo = this.br.readLine();
                        String[] userPassRole = userInfo.split(":");
                        if (userPassRole.length != 3) {
                            System.out.println("User data is not in correct form!");
                            System.exit(0);
                        }
                        else
                        {
                            //read user txt document
                            String readString;
                            BufferedReader usersBr = new BufferedReader(new FileReader(users_txt));
                            while ((readString = usersBr.readLine()) != null) {
                                String[] userPassRoleTxt = readString.split(":");
                                if (userPassRoleTxt.length != 3) {
                                    System.out.println("Text document for user data is not in correct form!");
                                    System.exit(0);
                                }
                                if (userPassRoleTxt[0].equalsIgnoreCase(userPassRole[0]) 
                                        && userPassRoleTxt[1].equals(userPassRole[1])
                                        && userPassRoleTxt[2].equalsIgnoreCase(userPassRole[2])) {
                                    clientState++;
                                    this.pw.println("Correct");
                                    System.out.println("Correct password.");
                                    this.role = userPassRoleTxt[0];
                                    this.userName = userPassRoleTxt[1];
                                    this.password = userPassRoleTxt[2];
                                    break;
                                }
                            }
                            if (clientState == 0) {
                                this.pw.println("Not correct");
                                System.out.println("Not correct password.");
                            }
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("Disconnected user.");
                        for (ServeConnectedClient cl : this.allClients) {
                            if (cl.getUserName().equals(this.userName)) {
                                this.allClients.remove(cl);
                                break;
                            }
                        }
                    }
                    break;
                case 1:
                    break;
                case 2: 
                    break;
                default:
                    break;
            }
        }
    }
    
    void updateClientStatus() {
     
    }
}
