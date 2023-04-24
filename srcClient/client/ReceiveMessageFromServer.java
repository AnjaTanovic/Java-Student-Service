package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anja Tanovic
 */
public class ReceiveMessageFromServer implements Runnable{
    
    Client parent;
    BufferedReader br;
    
    private int clientState;
    
    public ReceiveMessageFromServer(Client parent) {
        this.parent = parent;
        this.br = parent.getBr();
        this.clientState = 0;
    }
    
    @Override
    public void run() {        
        while(true) {
            String serverMessage;
            switch (clientState) {
                case 0:
                    try {
                        //check password correctness
                        serverMessage = this.br.readLine();
                        if (serverMessage.equals("Correct")) {
                            parent.loginSuccessful(true);
                            serverMessage = this.br.readLine();
                            if (serverMessage.startsWith("Students")) {
                                String[] studentsInfo = serverMessage.split(":");
                                String[] comboInfo = new String[studentsInfo.length-1];
                                for (int i = 0; i < comboInfo.length; i++)
                                    comboInfo[i] = studentsInfo[i+1];
                                parent.updateStudents(comboInfo);
                            }
                            serverMessage = this.br.readLine();
                            if (serverMessage.startsWith("Courses")) {
                                String[] coursesInfo = serverMessage.split(":");
                                String[] comboInfo = new String[coursesInfo.length-1];
                                for (int i = 0; i < comboInfo.length; i++)
                                    comboInfo[i] = coursesInfo[i+1];
                                parent.updateCourses(comboInfo);
                            }
                            clientState++;
                        }
                        else {
                            parent.loginSuccessful(false);
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("Server not available.");
                        Logger.getLogger(ReceiveMessageFromServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 1:
                    try {
                        //get informations about currect users and courses
                        serverMessage = this.br.readLine();
                        if (serverMessage.startsWith("New Student")) {
                            String[] studentsInfo = serverMessage.split(":");
                            String[] comboInfo = new String[studentsInfo.length-1];
                            for (int i = 0; i < comboInfo.length; i++)
                                comboInfo[i] = studentsInfo[i+1];
                            parent.updateStudents(comboInfo);
                        }
                        else if (serverMessage.startsWith("Student info#")){
                            String[] studentsInfo = serverMessage.split("#");
                            String info = studentsInfo[1] + "\n" + studentsInfo[2] + "\n" + studentsInfo[3] + "\n" + studentsInfo[4];
                            parent.writeStudentInfo(info);
                        }
                        else if (serverMessage.startsWith("New Course")) {
                            String[] coursesInfo = serverMessage.split(":");
                            String[] comboInfo = new String[coursesInfo.length-1];
                            for (int i = 0; i < comboInfo.length; i++)
                                comboInfo[i] = coursesInfo[i+1];
                            parent.updateCourses(comboInfo);
                        }
                        else if (serverMessage.startsWith("Course info#")){
                            String[] coursesInfo = serverMessage.split("#");
                            String info = coursesInfo[1] + "\n" + coursesInfo[2] + "\n" +coursesInfo[3] + "\n" +coursesInfo[4];
                            parent.writeCourseInfo(info);
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("Server not available.");
                        Logger.getLogger(ReceiveMessageFromServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    break;
            }
            
        } 
    }
    
}
