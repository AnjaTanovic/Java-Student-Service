package server;

import java.util.ArrayList;

/**
 *
 * @author anjat
 */
public class Course {
    
    private String name;
    private ArrayList<String> categoriesNames;
    private ArrayList<Integer> categoriesPoints;
    private ArrayList<Integer> minPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCategoriesNames() {
        return categoriesNames;
    }

    public void setCategoriesNames(ArrayList<String> categoriesNames) {
        this.categoriesNames = categoriesNames;
    }

    public ArrayList<Integer> getCategoriesPoints() {
        return categoriesPoints;
    }

    public void setCategoriesPoints(ArrayList<Integer> categoriesPoints) {
        this.categoriesPoints = categoriesPoints;
    }

    public ArrayList<Integer> getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(ArrayList<Integer> minPoints) {
        this.minPoints = minPoints;
    }

    public Course(String name, ArrayList<String> categories, ArrayList<Integer> categoriesPoints, ArrayList<Integer> minPoints) {
        this.name = name;
        this.categoriesNames = categories;
        this.categoriesPoints = categoriesPoints;
        this.minPoints = minPoints;
    }
}
