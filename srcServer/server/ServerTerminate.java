package server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anja Tanovic
 */
public class ServerTerminate implements Runnable {
    
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    
    ObjectOutputStream outCourses;
    ObjectOutputStream outStudents;
    
    private final String coursesTxt = "D:\\Anja\\FAKULTET\\Master\\Razvoj softvera za embeded operativne sisteme\\NetBeans\\Zadaci\\2\\Java-Student-Service\\srcServer\\server\\courses.txt";
    private final String studentsTxt = "D:\\Anja\\FAKULTET\\Master\\Razvoj softvera za embeded operativne sisteme\\NetBeans\\Zadaci\\2\\Java-Student-Service\\srcServer\\server\\students.txt";

    public ServerTerminate(ArrayList<Student> students, ArrayList<Course> courses) {
        this.students = students;
        this.courses = courses;
    }
    
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String userMess = sc.nextLine();
            if (userMess.equalsIgnoreCase("EXIT")) {
                System.out.println("Server terminated.");
                saveStudents();
                saveCourses();
                System.exit(0);
            }
            else {
                System.out.println("Only possible input for server is exit command.");
            }
        }
    }
    
    void saveStudents() {
        //serialization
        try {
            outStudents = new ObjectOutputStream(new FileOutputStream(studentsTxt));
            outStudents.writeObject(this.students);
            outStudents.close();
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (IOException ex1) {
            Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex1);
        }
        System.out.println("Students saved.");
    }
    
    void saveCourses() {
        //serialization
        try {
            outCourses = new ObjectOutputStream(new FileOutputStream(coursesTxt));
            outCourses.writeObject(this.courses);
            outCourses.close();
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (IOException ex1) {
            Logger.getLogger(ServeConnectedClient.class.getName()).log(Level.SEVERE, null, ex1);
        }
        System.out.println("Courses saved.");
    }
}
