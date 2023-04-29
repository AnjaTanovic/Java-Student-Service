package server;

import java.util.ArrayList;

/**
 *
 * @author Anja Tanovic
 */
public class StudentsCourse {
    
    private Course course;
    private int [] categoriesPoints;
    private int grade;

    public StudentsCourse(Course course) {
        this.course = course;
        this.categoriesPoints = new int[course.getCategoriesNames().size()];
        for (int i = 0; i < course.getCategoriesNames().size(); i++) {
            this.categoriesPoints[i] = 0;
        }
        this.grade = 5;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int[] getCategoriesPoints() {
        return categoriesPoints;
    }
    
    public int getCategoryPoints(int category) {
        return categoriesPoints[category];
    }

    public void setCategoriesPoints(int index, int points) {
        this.categoriesPoints[index] = points;
    }

    public int getGrade() {
        calculateGrade();
        return grade;
    }
    
    public int sumPoints() {
        int sum = 0;
        for (int i = 0; i < categoriesPoints.length; i++) {
            sum += categoriesPoints[i];
        }
        return sum;
    }
    
    public void calculateGrade() {
        int sum = sumPoints();
        boolean pass = true;
        //check all categories and their minimums
        ArrayList<Integer> minPoints = course.getMinPoints();
        for (int i = 0; i < categoriesPoints.length; i++) {
            if (categoriesPoints[i] < minPoints.get(i)) {
                pass = false;
                break;
            }
        }
        if (pass) {
            if (sum < 51)
                this.grade = 5;
            else if (sum < 61)
                this.grade = 6;
            else if (sum < 71)
                this.grade = 7;
            else if (sum < 81)
                this.grade = 8;
            else if (sum < 91)
                this.grade = 9;
            else
                this.grade = 10;
        }
        else
            this.grade = 5;
    }
}
