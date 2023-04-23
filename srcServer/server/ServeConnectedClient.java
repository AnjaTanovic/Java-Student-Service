package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    
    private String userName;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String jmbg;
    private String index;
    
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
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    
    public ServeConnectedClient(Socket socket, ArrayList<ServeConnectedClient> allClients, ArrayList<Student> students, ArrayList<Course> courses) {
        this.socket = socket;
        this.allClients = allClients;
        this.students = students;
        this.courses = courses;
        
        //get InputStream and OutputStream from socket
        try {
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
            this.pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
            
            //user data is still not known
            this.userName = "";
            this.password = "";
            this.role = "";
            this.jmbg = "";
            this.firstName = "";
            this.lastName = "";
            this.index = "";
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
                                if (userPassRoleTxt[0].equals(userPassRole[0]) 
                                        && userPassRoleTxt[1].equals(userPassRole[1])
                                        && userPassRoleTxt[2].equalsIgnoreCase(userPassRole[2])) {
                                    clientState++;
                                    this.pw.println("Correct");
                                    System.out.println("Correct password.");
                                    this.userName = userPassRoleTxt[0];
                                    this.password = userPassRoleTxt[1];
                                    this.role = userPassRoleTxt[2];
                                    
                                    //send student info to new client
                                    String allStudents = "Students";

                                    for (Student st : this.students) {
                                        allStudents += ":" + st.getFirstName() + " " + st.getLastName() + ", " + st.getIndex();
                                    }
                                    this.pw.println(allStudents);
                                    
                                    //send course info to new client
                                    String allCourses = "Courses";

                                    for (Course cr : this.courses) {
                                        allCourses += ":" + cr.getName();
                                    }
                                    this.pw.println(allCourses);
                                    
                                    break;
                                }
                            }
                            usersBr.close();
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
                                return;
                            }
                        }
                    }
                    break;
                case 1:
                    try {
                        //New student
                        String clientMess = this.br.readLine();
                        if (clientMess.equals("New student"))
                        {
                            String addStudentMess = this.br.readLine();
                            
                            String[] studentInfo = addStudentMess.split(":");
                            //newStudentFirstName:newStudentLastName:newStudentIndex:newStudentJMBG:newStudentUsername:newStudentPassword
                   
                            Student std = new Student(studentInfo[4], studentInfo[5], studentInfo[0], studentInfo[1], studentInfo[3], studentInfo[2]);
                            students.add(std);
                            
                            //send info to client
                            String allStudents = "New Student";
                            
                            for (Student st : this.students) {
                                allStudents += ":" + st.getFirstName() + " " + st.getLastName() + ", " + st.getIndex();
                            }
                            
                            this.pw.println(allStudents);
                            
                            //write users in file
                            String newFileInfo = studentInfo[4] + ":" + studentInfo[5] + ":" + "student";
                            try {
                                PrintWriter usersPw = new PrintWriter(new FileWriter(users_txt,true));
                                usersPw.println(newFileInfo);
                                usersPw.close();
                            } catch (IOException ex) {
                                Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else if (clientMess.equals("New admin"))
                        {
                            String addAdminMess = this.br.readLine();
                            
                            String[] adminInfo = addAdminMess.split(":");
                            //newAdminUsername:newAdminPassword
                            String newAdminUsername = adminInfo[0];
                            String newAdminPassword = adminInfo[1];
                            
                            //write users in file
                            String newFileInfo = newAdminUsername + ":" + newAdminPassword + ":" + "admin";
                            try {
                                PrintWriter usersPw = new PrintWriter(new FileWriter(users_txt,true));
                                usersPw.println(newFileInfo);
                                usersPw.close();
                            } catch (IOException ex) {
                                Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else if (clientMess.equals("New course"))
                        {
                            String addCourseMess = this.br.readLine();
                            
                            Course crs = new Course(addCourseMess);
                            courses.add(crs);
                            
                            //send info to client
                            String allCourses = "New Course";
                            
                            for (Course cr : this.courses) {
                                allCourses += ":" + cr.getName();
                            }
                            
                            this.pw.println(allCourses);
                        }    
                        else if (clientMess.startsWith("ComboStudent:")) 
                        {
                            String[] comboStud = clientMess.split(":");
                            
                            String studentNameIndex = comboStud[1];
                            for (Student st : this.students) {
                                if (studentNameIndex.equals(st.getFirstName() + " " + st.getLastName() + ", " + st.getIndex())) {
                                    this.pw.println("Student info#Name:" + st.getFirstName() + " " + st.getLastName() + 
                                            "#Index: " + st.getIndex() + "#JMBG: " + st.getJmbg());
                                }
                            }
                        }
                        else if (clientMess.startsWith("ComboCourse:")) 
                        {
                            String[] comboCour = clientMess.split(":");
                            
                            String courseName = comboCour[1];
                            for (Course cr : this.courses) {
                                if (courseName.equals(cr.getName())) {
                                    this.pw.println("Course info#Name:" + cr.getName());
                                }
                            }
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("Disconnected user.");
                        for (ServeConnectedClient cl : this.allClients) {
                            if (cl.getUserName().equals(this.userName)) {
                                this.allClients.remove(cl);
                                return;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
    
    void updateClientStatus() {
     
    }
}
