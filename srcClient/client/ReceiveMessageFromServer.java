package client;

import java.io.BufferedReader;
import java.io.IOException;

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
                            if (parent.getRole().equals("Admin")) 
                                clientState = 1;
                            else  
                                clientState = 2;
                        }
                        else {
                            parent.loginSuccessful(false);
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("Server not available.");
                        parent.printMess("Disconnected from server!");
                        parent.logout();
                        return;
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
                            String[] studentIndex = studentsInfo[2].split(":");
                            
                            //update text only if this student is selected
                            if (parent.checkCurrentStudentIndex(studentIndex[1].trim())) {
                                String[] coursesInfo = studentsInfo[5].split(":");
                                String infoCourse = "";
                                for (int i = 0; i< coursesInfo.length; i++) {
                                    infoCourse += " \n" + coursesInfo[i];
                                }
                                String info = studentsInfo[1] + "\n" + studentsInfo[2] + "\n" + studentsInfo[3] + "\n" + studentsInfo[4] + infoCourse;
                                parent.writeStudentInfo(info);
                            }
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
                            String[] coursesName = coursesInfo[1].split(":");
                            
                            //update text only if this course is selected
                            if (parent.checkCurrentCourseName(coursesName[1].trim())) {
                                String info = coursesInfo[1] + "\n" + coursesInfo[2] + "\n" +coursesInfo[3] + "\n" +coursesInfo[4];
                                parent.writeCourseInfo(info);
                            }
                        }
                        else if (serverMessage.startsWith("Error:")){
                            String[] mess = serverMessage.split(":");
                            parent.printMess(mess[1]);
                        }
                        else if (serverMessage.equals("Logout")) {
                            clientState = 3;
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("Server not available.");
                        parent.printMess("Disconnected from server!");
                        parent.logout();
                        return;
                    }
                    break;
                case 2:
                    try {
                        //get informations about currect user
                        serverMessage = this.br.readLine();
                        if (serverMessage.startsWith("Student info#")){
                            String[] studentsInfo = serverMessage.split("#");
                            
                            //update text only if this student is selected
                            if (parent.checkCurrentStudentIndex(studentsInfo[2])) {
                                String[] coursesInfo = studentsInfo[5].split(":");
                                String infoCourse = "";
                                for (int i = 0; i< coursesInfo.length; i++) {
                                    infoCourse += " \n" + coursesInfo[i];
                                }
                                String info = studentsInfo[1] + "\n" + studentsInfo[2] + "\n" + studentsInfo[3] + "\n" + studentsInfo[4] + infoCourse;
                                parent.writeStudentInfo(info);
                            }
                        }
                        else if (serverMessage.startsWith("Error:")){
                            String[] mess = serverMessage.split(":");
                            parent.printMess(mess[1]);
                        }
                        else if (serverMessage.equals("Logout")) {
                            clientState = 3;
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("Server not available.");
                        parent.printMess("Disconnected from server!");
                        parent.logout();
                        return;
                    }
                    break;
                case 3: 
                    return;
                default:
                    break;
            }
            
        } 
    }
    
}
