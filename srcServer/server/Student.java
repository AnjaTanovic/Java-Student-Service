package server;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Anja Tanovic
 */
public class Student implements Serializable {
    
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String jmbg;
    private String index;
    private ArrayList<StudentsCourse> courses;

    public Student(String userName, String password, String firstName, String lastName, String jmbg, String index) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
        this.index = index;
        this.courses = new ArrayList<>();
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

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
    
    public void addCourse(Course course) {
        courses.add(new StudentsCourse(course));
    }
    
    public ArrayList<String> getCourses() {
        ArrayList<String> studentCourses = new ArrayList<>();
        for (StudentsCourse course : courses) {
            studentCourses.add(course.getCourse().getName());
        }
        
        return studentCourses;
    }
    
    public void addPoints(Course course, String categoryName, int points) {
        
        for (StudentsCourse cr : courses) {
            if (cr.getCourse().getName().equals(course.getName())) {
                //get index of category
                int index = -1;
                for (int i = 0; i < course.getCategoriesNames().size(); i++) {
                    if (categoryName.equals(course.getCategoriesNames().get(i))) {
                        index = i;
                        break;
                    }
                }
                if (index != -1)
                    cr.setCategoriesPoints(index, points);
                else
                    System.out.println("Wrong category name!!");
                break;
            }
        }
    }
    
    public int getCourseGrade(Course course) {
        int grade = 0;
        for (StudentsCourse cr : courses) {
            if (cr.getCourse().getName().equals(course.getName())) {
                grade = cr.getGrade();
                break;
            }
        }
        return grade;
    }
    
    public String getCoursesInfo() {
        String names = "";
        for (int i = 0; i < courses.size(); i++) {
            names += "* " + courses.get(i).getCourse().getName() + " -> ";
                for (int j = 0; j < courses.get(i).getCourse().getCategoriesNames().size(); j++) {
                    names += courses.get(i).getCourse().getCategoriesNames().get(j) + "-" 
                            + courses.get(i).getCategoryPoints(j) + ", ";
                }
            names += "grade = " + courses.get(i).getGrade();
            if (i < courses.size() - 1) {
                names += ":";
            }
        }
        return names;
    }
}
