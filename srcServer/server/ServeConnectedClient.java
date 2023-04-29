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
                                    if (userPassRole[2].equals("Admin")) {
                                        clientState = 1;
                                        System.out.println("Admin is logged in");
                                    }
                                    else {
                                        clientState = 2; 
                                        System.out.println("Student is logged in");
                                    }
                                    this.pw.println("Correct");
                                    
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
                        return;
                    }
                    break;
                case 1:
                    try {
                        String clientMess = this.br.readLine();
                        //New student
                        if (clientMess.equals("New student")) {
                            String addStudentMess = this.br.readLine();
                            
                            String[] studentInfo = addStudentMess.split(":");
                            //newStudentFirstName:newStudentLastName:newStudentIndex:newStudentJMBG:newStudentUsername:newStudentPassword
                            
                            //check if new student has the same index, jmbg or username as other students
                            boolean ok = true;
                            for (Student st : students) {
                                if (st.getJmbg().equals(studentInfo[3]) 
                                        || st.getUserName().equals(studentInfo[4]))
                                {
                                    ok = false;
                                    break;
                                }
                                
                                String[] index11 = st.getIndex().split("/");
                                String[] index12 = st.getIndex().split("-");
                                String[] index21 = studentInfo[2].split("/");
                                String[] index22 = studentInfo[2].split("-");
                                if (index11.length == 2) {
                                    if (index21.length == 2) {
                                        if (index11[0].equals(index21[0]) && index11[1].equals(index21[1])) {
                                            ok = false;
                                            break;
                                        }
                                    }
                                    else {
                                        if (index11[0].equals(index22[0]) && index11[1].equals(index22[1])) {
                                            ok = false;
                                            break;
                                        }
                                    }
                                }
                                else {
                                    if (index21.length == 2) {
                                        if (index12[0].equals(index21[0]) && index12[1].equals(index21[1])) {
                                            ok = false;
                                            break;
                                        }
                                    }
                                    else {
                                        if (index12[0].equals(index22[0]) && index12[1].equals(index22[1])) {
                                            ok = false;
                                            break;
                                        }
                                    }    
                                }
                            }
                            
                            if (ok) {
                                Student std = new Student(studentInfo[4], studentInfo[5], studentInfo[0], studentInfo[1], studentInfo[3], studentInfo[2]);
                                students.add(std);

                                //send info to client
                                String allStudents = "New Student";

                                for (Student st : this.students) {
                                    allStudents += ":" + st.getFirstName() + " " + st.getLastName() + ", " + st.getIndex();
                                }

                                for (ServeConnectedClient client : allClients) {
                                    client.pw.println(allStudents);
                                }

                                //write users in file
                                String newFileInfo = studentInfo[4] + ":" + studentInfo[5] + ":" + "student";
                                try {
                                    PrintWriter usersPw = new PrintWriter(new FileWriter(users_txt,true));
                                    usersPw.println(newFileInfo);
                                    usersPw.close();
                                } 
                                catch (IOException ex) {
                                    Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
                                }    
                            }
                            else
                                this.pw.println("Error:Student has index, jmbg or username that already exists among other students!");
                        }
                        //New admin
                        else if (clientMess.equals("New admin")) {
                            String addAdminMess = this.br.readLine();
                            
                            String[] adminInfo = addAdminMess.split(":");
                            //newAdminUsername:newAdminPassword
                            String newAdminUsername = adminInfo[0];
                            String newAdminPassword = adminInfo[1];
                            
                            String newFileInfo = newAdminUsername + ":" + newAdminPassword + ":" + "admin";
                            
                            //check if user with the same username exists
                            boolean ok = true;
                            String readString;
                            BufferedReader usersBr = new BufferedReader(new FileReader(users_txt));
                            while ((readString = usersBr.readLine()) != null) {
                                String[] readAdmin = readString.split(":");
                                if (newAdminUsername.equals(readAdmin[0])) {
                                    ok = false;
                                    break;
                                }
                            }
                            usersBr.close();
                            
                            //write users in file
                            if (ok) {
                                try {
                                    PrintWriter usersPw = new PrintWriter(new FileWriter(users_txt,true));
                                    usersPw.println(newFileInfo);
                                    usersPw.close();
                                } 
                                catch (IOException ex) {
                                    Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else {
                                this.pw.println("Error:Admin with the same username already exists!");
                            }
                            
                        }
                        //New Course
                        else if (clientMess.equals("New course")) {
                            String addCourseMess = this.br.readLine();
                            
                            String[] courseInfo = addCourseMess.split(":");
                            //newCourseName:categoriesNames:categoriesPoints:categoriesMinPoints
                            
                            //check if new course has the same name as other courses
                            boolean ok = true;
                            for (Course cr : courses) {
                                if (cr.getName().equals(courseInfo[0]))
                                {
                                    ok = false;
                                    break;
                                }
                            }
                            
                            if (ok) {
                                String[] categoryNames = courseInfo[1].split(",");
                                ArrayList<String> catNames = new ArrayList<>();
                                for (String categoryName : categoryNames) {
                                    catNames.add(categoryName.trim());
                                }
                                String[] categoryPoints = courseInfo[2].split(",");
                                ArrayList<Integer> catPoints = new ArrayList<>();
                                for (String points : categoryPoints) {
                                    catPoints.add(Integer.parseInt(points.trim()));
                                }
                                String[] categoryMinPoints = courseInfo[3].split(",");
                                ArrayList<Integer> catMinPoints = new ArrayList<>();
                                for (String points : categoryMinPoints) {
                                    catMinPoints.add(Integer.parseInt(points.trim()));
                                }

                                Course crs = new Course(courseInfo[0], catNames, catPoints, catMinPoints);
                                courses.add(crs);

                                //send info to client
                                String allCourses = "New Course";

                                for (Course cr : this.courses) {
                                    allCourses += ":" + cr.getName();
                                }

                                for (ServeConnectedClient client : allClients) {
                                    client.pw.println(allCourses);
                                }    
                            }
                            else {
                                this.pw.println("Error:A course with the same name already exists!");
                            }
                        }    
                        //Send informations about student selected by combo box
                        else if (clientMess.startsWith("ComboStudent:")) {
                            String[] comboStud = clientMess.split(":");
                            
                            String studentNameIndex = comboStud[1];
                            for (Student st : this.students) {
                                String studentsCourses = st.getCoursesInfo();
                                if (studentsCourses.equals(""))
                                    studentsCourses = "* No courses";
                                if (studentNameIndex.equals(st.getFirstName() + " " + st.getLastName() + ", " + st.getIndex())) {
                                    for (ServeConnectedClient client : allClients) {
                                        String message = "Student info#Name: " + st.getFirstName() + " " + st.getLastName() + 
                                            "#Index: " + st.getIndex() + "#JMBG: " + st.getJmbg() + "#Courses:#" + studentsCourses;
                                        client.pw.println(message);
                                    }
                                }
                            }
                        }
                        //Send informations about course selected by combo box
                        else if (clientMess.startsWith("ComboCourse:")) {
                            String[] comboCour = clientMess.split(":");
                            
                            String courseName = comboCour[1];
                            for (Course cr : this.courses) {
                                if (courseName.equals(cr.getName())) {
                                    String categories = "";
                                    ArrayList<String> allCategories = cr.getCategoriesNames();
                                    for (int i = 0; i < allCategories.size(); i++)
                                    {
                                        if (i < allCategories.size() - 1)
                                            categories +=  allCategories.get(i) + ", ";
                                        else
                                            categories +=  allCategories.get(i);
                                    }
                                    String points = "";
                                    ArrayList<Integer> allPoints = cr.getCategoriesPoints();
                                    for (int i = 0; i < allPoints.size(); i++)
                                    {
                                        if (i < allPoints.size() - 1)
                                            points +=  allPoints.get(i) + ", ";
                                        else
                                            points +=  allPoints.get(i);
                                    }
                                    String minPoints = "";
                                    ArrayList<Integer> allMinPoints = cr.getMinPoints();
                                    for (int i = 0; i < allMinPoints.size(); i++)
                                    {
                                        if (i < allMinPoints.size() - 1)
                                            minPoints +=  allMinPoints.get(i) + ", ";
                                        else
                                            minPoints +=  allMinPoints.get(i);
                                    }
                                    for (ServeConnectedClient client : allClients) {
                                        String message = "Course info#Name: " + cr.getName() + "#Categories: " + categories + 
                                            "#Points for each category: " + points + "#Minimum points for each category: " + minPoints;
                                        client.pw.println(message);
                                    }
                                }
                            }
                        }
                        //Add new course to students courses
                        else if (clientMess.startsWith("Add new Course:")) {
                            String[] courseStudent = clientMess.split(":");
                            //Add new Course:newCourse:for student:comboStudent
                            
                            for (Student st : this.students) {
                                if (courseStudent[3].equals(st.getFirstName() + " " + st.getLastName() + ", " + st.getIndex())) {
                                    for (Course cr : this.courses) {
                                        //find course to add
                                        if (courseStudent[1].equals(cr.getName())) {
                                            //check if this course is already in students courses
                                            ArrayList<String> studCourses = st.getCourses();
                                            System.out.println(studCourses);
                                            if (studCourses.isEmpty()) {
                                                    st.addCourse(cr);
                                                    System.out.println("New course for student");
                                                }
                                                
                                            for (int i = 0; i < studCourses.size(); i++) {
                                                if (studCourses.get(i).equals(courseStudent[1])) {
                                                    System.out.println("Course already in students courses");
                                                    break;
                                                }
                                                if (i == studCourses.size() - 1) {
                                                    st.addCourse(cr);
                                                    System.out.println("New course for student");
                                                }
                                            }
                                            break;
                                        }
                                    }
                                break;    
                                }
                            }
                            
                            String studentNameIndex = courseStudent[3];
                            for (Student st : this.students) {
                                String studentsCourses = st.getCoursesInfo();
                                if (studentsCourses.equals(""))
                                    studentsCourses = "* No courses";
                                if (studentNameIndex.equals(st.getFirstName() + " " + st.getLastName() + ", " + st.getIndex())) {
                                    for (ServeConnectedClient client : allClients) {
                                        String message = "Student info#Name: " + st.getFirstName() + " " + st.getLastName() + 
                                            "#Index: " + st.getIndex() + "#JMBG: " + st.getJmbg() + "#Courses:#" + studentsCourses;
                                        client.pw.println(message);
                                    }
                                }
                            }
                            
                        }
                        //Grade student
                        else if (clientMess.startsWith("Grade:")) {
                            //Grade:comboStudent:courseToGrade:categoryToGrade:studentPoints
                            String[] gradeStud = clientMess.split(":");
                            int points = Integer.parseInt(gradeStud[4]);
                            
                            boolean correctGrade = false;
                            //check if course category exists and check if number 
                            //of points are < or == then number of points in category
                            Course chosenCourse = new Course(null, null, null, null);
                            for (Course cr : this.courses) {
                                if (gradeStud[2].equals(cr.getName())) {
                                    chosenCourse = cr;
                                    ArrayList<String> catNames = cr.getCategoriesNames();
                                    ArrayList<Integer> catPoints = cr.getCategoriesPoints();
                                    for (int i = 0; i < catNames.size(); i++) {
                                        if (catNames.get(i).equals(gradeStud[3]) && catPoints.get(i) >= points) {
                                            correctGrade = true;
                                            break;
                                        }
                                    }
                                break;    
                                }
                            }
                            //grade student
                            if (correctGrade) {
                                for (Student st : this.students) {
                                    if (gradeStud[1].equals(st.getFirstName() + " " + st.getLastName() + ", " + st.getIndex())) {
                                        st.addPoints(chosenCourse, gradeStud[3], points);
                                        System.out.println("Student graded");
                                        break;
                                    }
                                    break;    
                                }
                            }
                            else {
                                this.pw.println("Error:Points and category for course are not correct!");
                            }
                            //update info
                            String studentNameIndex = gradeStud[1];
                            for (Student st : this.students) {
                                String studentsCourses = st.getCoursesInfo();
                                if (studentsCourses.equals(""))
                                    studentsCourses = "* No courses";
                                if (studentNameIndex.equals(st.getFirstName() + " " + st.getLastName() + ", " + st.getIndex())) {
                                    for (ServeConnectedClient client : allClients) {
                                        String message = "Student info#Name: " + st.getFirstName() + " " + st.getLastName() + 
                                            "#Index: " + st.getIndex() + "#JMBG: " + st.getJmbg() + "#Courses:#" + studentsCourses;
                                        client.pw.println(message);
                                    }
                                }
                            }
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("Disconnected user.");
                        return;
                    }
                    break;
                case 2:
                    try {
                        String clientMess = this.br.readLine();
                        //Get info about currect student
                        if (clientMess.startsWith("GetInfo:")) {
                            //GetInfo:studentUsername
                            String[] currentStudent = clientMess.split(":");

                            //update info
                            for (Student st : this.students) {                               
                                if (currentStudent[1].equals(st.getUserName())) {
                                    
                                    String studentsCourses = st.getCoursesInfo();
                                    if (studentsCourses.equals(""))
                                        studentsCourses = "* No courses";

                                    for (ServeConnectedClient client : allClients) {
                                        String message = "Student info#Name: " + st.getFirstName() + " " + st.getLastName() + 
                                            "#Index: " + st.getIndex() + "#JMBG: " + st.getJmbg() + "#Courses:#" + studentsCourses;
                                        client.pw.println(message);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("Disconnected user.");
                        return;
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
